package clean.platform.disorder.provider;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IOrderFacade;
import clean.platform.disorder.mapper.TbFinishedOrderMapper;
import clean.platform.disorder.mapper.TbOrderMapper;
import clean.platform.disorder.mapper.TbStaffOrderMapper;
import clean.platform.disorder.mapper.extend.TbFinishedOrderExtendMapper;
import clean.platform.disorder.mapper.extend.TbOrderExtendMapper;
import clean.platform.disorder.model.ConstDefiner;
import clean.platform.disorder.model.dto.OrderVo;
import clean.platform.disorder.model.entity.TbFinishedOrder;
import clean.platform.disorder.model.entity.TbOrder;
import clean.platform.disorder.model.entity.TbStaffOrder;
import clean.platform.disorder.model.entity.vo.JudgeFinishedOrderVo;
import clean.platform.disorder.model.entity.vo.TbFinishedOrderVo;
import clean.platform.disorder.model.entity.vo.TbOrderVo;
import clean.platform.disorder.model.entity.vo.TbStaffVo;
import clean.platform.disorder.model.query.QueryOrder;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.jvm.hotspot.utilities.RBNode;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static clean.platform.disorder.model.ConstDefiner.STATUS_AGAIN;
import static clean.platform.disorder.model.ConstDefiner.STATUS_CANCEL;
import static clean.platform.disorder.model.ConstDefiner.STATUS_FINISHED;
import static clean.platform.disorder.model.ResultEnum.*;

@Service
public class OrderService extends BaseService implements IOrderFacade {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderExtendMapper orderExtendMapper;

    @Autowired
    private TbStaffOrderMapper staffOrderMapper;

    @Autowired
    private TbFinishedOrderMapper finishedOrderMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOrder(OrderVo orderVo) throws OperationException {
        try {
            String zhang = "zhang";
           zhang = new String(zhang.getBytes());
            orderVo.setId(getId());
            String detailInfo = StringUtils.join(orderVo.getItemsName(),",");
            orderVo.setDetailInfo(detailInfo);
            orderVo.setOrderNo(String.valueOf(System.currentTimeMillis())); //订单流水号
            orderVo.setStatus(ConstDefiner.STATUS_NEW);
            orderMapper.insertSelective(orderVo);
        } catch(Exception e) {
            logger.error("保存订单信息异常:{}",e);
            throw new OperationException(RESULT_ORDER_ADD_ERROR,e);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrder(OrderVo orderVo) throws OperationException {
        List<TbOrderVo> tbOrderVos = orderExtendMapper.selectByVo(orderVo);
        if(tbOrderVos.size() > 0){
            TbOrderVo dbOrderVo = tbOrderVos.get(0);
            if(dbOrderVo.getId().longValue() != orderVo.getId().longValue()){
                throw new OperationException(RESULT_ORDER_DUPLICATE_NAME);
            }
        }
        try{
            orderMapper.updateByPrimaryKeySelective(orderVo);
        } catch(Exception e){
            logger.error("更新订单操作失败:{}", e);
            throw new OperationException(RESULT_ORDER_UPDATE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(Long id) throws OperationException {
        clean.platform.disorder.model.entity.TbOrder order = orderExtendMapper.selectByPK(id);
        try{
            orderMapper.deleteByPrimaryKey(id);
        } catch(Exception e){
            logger.error("订单删除失败：{}", e);
            throw new OperationException("订单删除失败",e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TbOrderVo queryOrderInfo(Long id) throws OperationException {
        try{
            TbOrderVo tbOrderVo = orderExtendMapper.selectByPK(id);
            return tbOrderVo;
        } catch(Exception e){
            logger.error("查询订单失败:{}", e);
            throw new OperationException("查询订单异常", e);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<TbOrderVo> queryOrderPageList(QueryOrder queryOrder) throws OperationException {
        PageInfo<TbOrderVo> pageInfo = new PageInfo<>();
        try{
            TbOrderVo tbOrderVo = new TbOrderVo();
            if (!StringUtils.isEmpty(queryOrder.getCustomerName())) {
                tbOrderVo.setCustomerName(queryOrder.getCustomerName());
            }
            if (queryOrder.getOrderStatus() != null && queryOrder.getOrderStatus()>0){
                tbOrderVo.setStatus(queryOrder.getOrderStatus());
            }
            fillOrderQuery(queryOrder, tbOrderVo);
            Page<TbOrderVo> pageOrders = PageHelper.startPage(queryOrder.getPageNo(), queryOrder.getPageSize())
                    .doSelectPage(new ISelect() {
                        @Override
                        public void doSelect() {
                            List<TbOrderVo> tbOrderVos = orderExtendMapper.selectOrderByVo(tbOrderVo);

                        }
                    });
            List<TbOrderVo> tbOrderVos = orderExtendMapper.selectOrderByVo(tbOrderVo);

            queryOrderPager(pageOrders);
            pageInfo = new PageInfo<>(pageOrders);
            return pageInfo;
        } catch (Exception e){
            logger.error("分页查询订单信息失败:{}", e);
            throw new OperationException("分页查询订单信息失败", e);
        }
    }
    //2020-2-2 财务审核
    @Override
    public PageInfo<JudgeFinishedOrderVo> judgeFinishOrder(QueryOrder queryOrder) throws OperationException {

        PageInfo<JudgeFinishedOrderVo> pageInfo = new PageInfo<>();
        try{
            JudgeFinishedOrderVo tbOrderVo = new JudgeFinishedOrderVo();
//            if (!StringUtils.isEmpty(queryOrder.getCustomerName())) {
//                tbOrderVo.getCreateUser(queryOrder.getCustomerName());
//            }
            if (queryOrder.getOrderStatus() != null && queryOrder.getOrderStatus()>-1){
                tbOrderVo.setStatus(queryOrder.getOrderStatus());
            }
            fillJudgeOrderQuery(queryOrder, tbOrderVo);
            Page<JudgeFinishedOrderVo> pageOrders = PageHelper.startPage(queryOrder.getPageNo(), queryOrder.getPageSize())
                    .doSelectPage(new ISelect() {
                        @Override
                        public void doSelect() {
                            List<JudgeFinishedOrderVo> tbOrderVos = orderExtendMapper.selectJudgeFinsihOrder(tbOrderVo);

                        }
                    });

            queryJudgeOrderPager(pageOrders);
            pageInfo = new PageInfo<>(pageOrders);
            return pageInfo;
        } catch (Exception e){
            logger.error("分页查询订单信息失败:{}", e);
            throw new OperationException("分页查询订单信息失败", e);
        }

    }

    @Override
    public List<Map<String,String>> selectOrderNumber() throws OperationException {
        List<Map<Integer,Integer>> map =  new ArrayList<>();
        List<Map<String,String>> map3=new ArrayList<>();

        try{
            map = orderExtendMapper.selectOrderNumber();
            if(map.size()>0){
                for (int i = 0; i < map.size(); i++) {
                    int j=0;

                    Map<String,String> map2 =  new HashMap<>();
                    for (Map.Entry<Integer, Integer> entry : map.get(i).entrySet()) {
                        if(j==0){
                            map2.put("value",entry.getValue()+"");
                        }
                        if(((Number)entry.getValue()).intValue()==5){
                            map2.put("name","订单作废数");
                        }else if(((Number)entry.getValue()).intValue()==3){
                            map2.put("name","成交数");
                        }else if(((Number)entry.getValue()).intValue()==4){
                            map2.put("name","审核作废数");
                        }else if(((Number)entry.getValue()).intValue()==8){
                            map2.put("name","重派数");
                        }else if(((Number)entry.getValue()).intValue()==12){
                            map2.put("name","删除数");
                        }else{
                            map2.put("name","未知");
                        }
                        j++;
                    }
                    map3.add(map2);
                }
            }

        } catch(Exception e){
            logger.error("查询订单失败:{}", e);
            throw new OperationException("查询订单异常", e);
        }
        return map3;
    }

    @Override
    public List<Map<String, Integer>> selectOrderFinishNumber() throws OperationException {
        List<Map<String, Integer>> map =  new ArrayList<>();
        List<Map<String,Integer>> map3=new ArrayList<>();

        try{
            map = orderExtendMapper.selectOrderFinishNumber();
            if(map.size()>0){
                for (int i = 0; i < map.size(); i++) {

                    int j=0;
                    Map<String,Integer> map2 =  new HashMap<>();
                    for (Map.Entry<String, Integer> entry : map.get(i).entrySet()) {
                       map2.put(entry.getKey(),Integer.parseInt(((Number)entry.getValue()).toString()));
                    }
                    map3.add(map2);
                }

            }
        } catch(Exception e){
            logger.error("查询订单失败:{}", e);
            throw new OperationException("查询订单异常", e);
        }
        return map3;

    }

    @Override
    public List<Map<String, Integer>> selectFirstOrderFinishNumber() throws OperationException {
        List<Map<String, Integer>> map =  new ArrayList<>();
        List<Map<String,Integer>> map3=new ArrayList<>();

        try{
            map = orderExtendMapper.selectFirstOrderFinishNumber();
            if(map.size()>0){
                for (int i = 0; i < map.size(); i++) {

                    int j=0;
                    Map<String,Integer> map2 =  new HashMap<>();
                    for (Map.Entry<String, Integer> entry : map.get(i).entrySet()) {
                        map2.put(entry.getKey(),Integer.parseInt(((Number)entry.getValue()).toString()));
                    }
                    map3.add(map2);
                }

            }

        } catch(Exception e){
            logger.error("查询订单失败:{}", e);
            throw new OperationException("查询订单异常", e);
        }
        return map3;
    }

    @Override
    public List<Map<String, Integer>> selectSevenOrderNumber() throws OperationException {
        List<Map<String, Integer>> map =  new ArrayList<>();
        List<Map<String,Integer>> map3=new ArrayList<>();

        try{
            map = orderExtendMapper.selectSevenOrderNumber();

            if(map.size()>0){
                for (int i = 0; i < map.size(); i++) {

                    int j=0;
                    Map<String,Integer> map2 =  new HashMap<>();
                    for (Map.Entry<String, Integer> entry : map.get(i).entrySet()) {
                            map2.put(entry.getKey(),Integer.parseInt(((Number)entry.getValue()).toString()));
                    }
                    map3.add(map2);
                }

            }

        } catch(Exception e){
            logger.error("查询订单失败:{}", e);
            throw new OperationException("查询订单异常", e);
        }
        return map3;

    }
    @Override
    public List<Map<String, String>> selectSevenOrderNumber2() throws OperationException {
        List<Map<String, String>> map4 =  new ArrayList<>();
        List<Map<String,String>> map3=new ArrayList<>();
        try{
            map4 = orderExtendMapper.selectSevenOrderNumber2();
            if(map4.size()>0){
                for (int i = 0; i < map4.size(); i++) {
                    System.out.println(map4.get(i));

                    Map<String,String> map2 =  new HashMap<>();
                    for (Map.Entry<String, String> entry : map4.get(i).entrySet()) {
                        map2.put(entry.getKey(),entry.getValue());
                    }
                    map3.add(map2);
                }

            }
        } catch(Exception e){
            logger.error("查询订单失败:{}", e);
            throw new OperationException("查询订单异常", e);
        }
        return map3;

    }

    @Override
    public List<Map<String, String>> selectOrinigOrderNumber() throws OperationException {
        List<Map<Integer, Integer>> map =  new ArrayList<>();
        List<Map<String,String>> map3=new ArrayList<>();

        try{
            map = orderExtendMapper.selectOrinigOrderNumber();
            if(map.size()>0){
                if(map.size()>0){
                    for (int i = 0; i < map.size(); i++) {
                        int j=0;

                        Map<String,String> map2 =  new HashMap<>();
                        for (Map.Entry<Integer, Integer> entry : map.get(i).entrySet()) {
                            if(j==0){
                                map2.put("value",entry.getValue()+"");
                            }
                            if(((Number)entry.getValue()).intValue()==1){
                                map2.put("name","58放心服务");
                            }else if(((Number)entry.getValue()).intValue()==2){
                                map2.put("name","58到家");
                            }else if(((Number)entry.getValue()).intValue()==3){
                                map2.put("name","熟人介绍");
                            }else if(((Number)entry.getValue()).intValue()==4){
                                map2.put("name","回头客");
                            }else if(((Number)entry.getValue()).intValue()==5){
                                map2.put("name","蜜蜂帮帮");
                            }else{
                                map2.put("name","未知");
                            }
                            j++;
                        }
                        map3.add(map2);
                    }
                }
            }

        } catch(Exception e){
            logger.error("查询订单失败:{}", e);
            throw new OperationException("查询订单异常", e);
        }
        return map3;

    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<TbOrderVo> queryNotDisOrderPageList(QueryOrder queryOrder) throws OperationException {
        PageInfo<TbOrderVo> pageInfo = new PageInfo<>();
        try{
            TbOrderVo tbOrderVo = new TbOrderVo();
            if (!StringUtils.isEmpty(queryOrder.getCustomerName())) {
                tbOrderVo.setCustomerName(queryOrder.getCustomerName());
            }
            if (queryOrder.getOrderStatus() != null && queryOrder.getOrderStatus()>0){
                tbOrderVo.setStatus(queryOrder.getOrderStatus());
            }
            fillOrderQuery(queryOrder, tbOrderVo);
            Page<TbOrderVo> pageOrders = PageHelper.startPage(queryOrder.getPageNo(), queryOrder.getPageSize())
                    .doSelectPage(() -> orderExtendMapper.selectNotDisOrderByVo(tbOrderVo));
            queryOrderPager(pageOrders);
            pageInfo = new PageInfo<>(pageOrders);
            return pageInfo;
        } catch (Exception e){
            logger.error("分页查询订单信息失败:{}", e);
            throw new OperationException("分页查询订单信息失败", e);
        }
    }

    private void queryOrderPager(Page<TbOrderVo> pageOrders) {
        pageOrders.getResult().stream().filter((TbOrderVo order) ->order.getStatus()>1).forEach((TbOrderVo order) ->{
            Long orderId = order.getId();
            List<TbStaffVo> staffVos = orderExtendMapper.selectOrderStaff(orderId);
            List<String> list = new ArrayList<>();
            for (TbStaffVo vo : staffVos) {
                String staffName = vo.getStaffName();
                list.add(staffName);
            }
            String staffs = StringUtils.join(list.toArray(),",");
            order.setStaffsName(staffs);
        });
    }

    private void queryJudgeOrderPager(Page<JudgeFinishedOrderVo> pageOrders) {
        pageOrders.getResult().stream().filter((JudgeFinishedOrderVo order) ->order.getStatus()>-1).forEach((JudgeFinishedOrderVo order) ->{
            Long orderId = order.getId();
            List<TbStaffVo> staffVos = orderExtendMapper.selectOrderStaff(orderId);
            List<String> list = new ArrayList<>();
            for (TbStaffVo vo : staffVos) {
                String staffName = vo.getStaffName();
                list.add(staffName);
            }
            String staffs = StringUtils.join(list.toArray(),",");
            order.setStaffsName(staffs);
        });
    }

    private void fillOrderQuery(QueryOrder queryOrder, TbOrderVo tbOrderVo) {
        if (queryOrder.getStartDate()!=null && queryOrder.getStartDate().length>0){
            Date[] queryDate = queryOrder.getStartDate();
            if (queryDate[0].compareTo(queryDate[1])>=0){
                tbOrderVo.setQueryEndDate(queryDate[0]);
                tbOrderVo.setQueryEndDate(queryDate[1]);
            } else {
                tbOrderVo.setQueryEndDate(queryDate[1]);
                tbOrderVo.setQueryEndDate(queryDate[0]);
            }
        }
    }
    private void fillJudgeOrderQuery(QueryOrder queryOrder, JudgeFinishedOrderVo judgeFinishedOrderVo) {
        if (queryOrder.getStartDate()!=null && queryOrder.getStartDate().length>0){
            Date[] queryDate = queryOrder.getStartDate();
            if (queryDate[0].compareTo(queryDate[1])>=0){
                judgeFinishedOrderVo.setQueryEndDate(queryDate[0]);
                judgeFinishedOrderVo.setQueryEndDate(queryDate[1]);
            } else {
                judgeFinishedOrderVo.setQueryEndDate(queryDate[1]);
                judgeFinishedOrderVo.setQueryEndDate(queryDate[0]);
            }
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dispatchOrder(TbOrderVo orderVo) throws OperationException {
        try {
            final Long orderId = orderVo.getId();
            Long[] staffIds = orderVo.getStaffIds();
            List<TbStaffOrder> staffOrders = Arrays.stream(staffIds).map(staffId -> {
                TbStaffOrder staffOrder = new TbStaffOrder();
                staffOrder.setOrderId(orderId);
                staffOrder.setStaffId(staffId);
                return staffOrder;
            }).collect(Collectors.toList());
            orderVo.setStatus(ConstDefiner.STATUS_DISPATCH);
            orderMapper.updateByPrimaryKeySelective(orderVo);
            staffOrderMapper.insertBatch(staffOrders);
        } catch (Exception e){
            logger.error("订单分派异常:{}",e);
            throw new OperationException("订单分派异常",e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finishOrder(TbFinishedOrderVo finishedOrderVo) throws OperationException {
        try {
            Long orderId = finishedOrderVo.getOrderId();
            orderExtendMapper.updateOrderStatus(orderId, STATUS_FINISHED);
            finishedOrderVo.setCreateDate(getNowDate());
            finishedOrderVo.setId(getId());
            finishedOrderMapper.insertSelective(finishedOrderVo);
        } catch (Exception e){
            logger.error("结束订单状态异常:{}",e);
            throw new OperationException("结束订单状态异常",e);
        }
    }

    @Override
    public void cancelOrder(Long orderId) throws OperationException {
        try {
            orderExtendMapper.updateOrderStatus(orderId, STATUS_CANCEL);
        } catch (Exception e){
            logger.error("取消下单异常:{}",e);
            throw  new  OperationException("取消下单异常",e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void againOrder(Long orderId) throws OperationException {
        try {
            orderExtendMapper.updateOrderStatus(orderId, STATUS_AGAIN);
        } catch (Exception e){
            logger.error("重新分派订单异常:{}",e);
            throw  new  OperationException("重新分派订单异常",e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetDisOrder(Long orderId) throws OperationException {
        try {
            //如果时重新下派订单,则需要新建订单,并把订单保存源来订单ID
//            TbOrderVo tbOrderVo = orderExtendMapper.selectByPK(orderId);
//            Long originOrderId = tbOrderVo.getId();
//            tbOrderVo.setOriginOrderId(originOrderId);
//            tbOrderVo.setId(getId());
//            tbOrderVo.setStatus((short)2);
            orderExtendMapper.updateOrderStatus(orderId,(short)2);
        } catch (Exception e){
            logger.error("重新分配下发订单异常:{}",e);
            throw new OperationException("重新分配下发订单异常",e);
        }
    }

    @Override
    public void checkDisOrder(Long orderId) throws OperationException {
        try {
            //表名8已审核完成
            orderExtendMapper.updateOrderStatus(orderId,(short)8);
            //3代表审核成功，
            orderExtendMapper.updateFinishOrderStatus(orderId,(short)3);
        } catch (Exception e){
            logger.error("订单审核订单异常:{}",e);
            throw new OperationException("订单审核订单异常",e);
        }
    }

    @Override
    public void callBackOrder(Long orderId) throws OperationException {
        try {
            //表名8已审核完成
            orderExtendMapper.updateOrderStatus(orderId,(short)8);
            //3代表审核成功，
            orderExtendMapper.updateFinishOrderStatus(orderId,(short)4);
        } catch (Exception e){
            logger.error("订单审核订单异常:{}",e);
            throw new OperationException("订单审核订单异常",e);
        }
    }



}
