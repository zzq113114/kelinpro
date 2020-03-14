package clean.platform.disorder.controller;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IOrderFacade;
import clean.platform.disorder.model.ResponseResult;
import clean.platform.disorder.model.ResultEnum;
import clean.platform.disorder.model.dto.OrderVo;
import clean.platform.disorder.model.entity.TbFinishedOrder;
import clean.platform.disorder.model.entity.TbOrder;
import clean.platform.disorder.model.entity.vo.JudgeFinishedOrderVo;
import clean.platform.disorder.model.entity.vo.TbFinishedOrderVo;
import clean.platform.disorder.model.entity.vo.TbOrderVo;
import clean.platform.disorder.model.query.QueryOrder;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static clean.platform.disorder.model.ResultEnum.*;

/*
* 订单管理主控类
* author：zz
* 2019-03-16
 */


@RestController
@RequestMapping("/order")
public class OrderController {
    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private IOrderFacade orderFacade;

    @RequestMapping("/add")
    public ResponseResult<?> addOrder(@RequestBody OrderVo orderVo) {
        ResponseResult<?> result = new ResponseResult<>();
        try{
            orderFacade.addOrder(orderVo);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch(OperationException e) {
            logger.error("新增订单操作异常：{}", e);
            result.setResultEnum(ResultEnum.RESULT_ORDER_ADD_ERROR);
        }
        return result;
    }

    @RequestMapping("/modify")
    public ResponseResult<?> updateOrder(@RequestBody OrderVo orderVo) {
        ResponseResult<?> result = new ResponseResult<>();
        try{
            orderFacade.updateOrder(orderVo);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch(OperationException e){
            logger.error("订单更新操作异常", e);
            result.setResultEnum(ResultEnum.RESULT_ORDER_UPDATE_ERROR);
        }
        return result;
    }

    @RequestMapping("/delete/{id}")
    public ResponseResult<?> deleteOrder(@PathVariable("id")Long orderID){
        ResponseResult<?> result = new ResponseResult<>();
        try{
            orderFacade.deleteOrder(orderID);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch(OperationException e) {
            logger.error("订单删除操作失败", e);
            result.setResultEnum(ResultEnum.RESULT_ORDER_DELETE_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/orderPage", method = RequestMethod.POST)
    public ResponseResult<PageInfo<TbOrderVo>> queryOrderPageList(@RequestBody QueryOrder queryOrder){
        ResponseResult<PageInfo<TbOrderVo>> result = new ResponseResult<>();
        try{
            PageInfo<TbOrderVo> pageOrder = orderFacade.queryOrderPageList(queryOrder);
            result.setData(pageOrder);
        } catch(OperationException e){
            logger.error("分页查询订单信息失败", e);
            result.setResultEnum(ResultEnum.RESULT_ORDER_QUERY_ERROR);
        }
        return result;
    }
// 2020-2-2 财务审核 -----------------------------------------
    @RequestMapping(value = "judgeFinishOrder",method = RequestMethod.POST)
    public  ResponseResult<PageInfo<JudgeFinishedOrderVo>> judegeOrder(@RequestBody QueryOrder queryOrder){
        ResponseResult<PageInfo<JudgeFinishedOrderVo>> result = new ResponseResult<>();
        try {
            PageInfo<JudgeFinishedOrderVo> tbFinishedOrder = orderFacade.judgeFinishOrder(queryOrder);
            result.setData(tbFinishedOrder);
        } catch (OperationException e) {
            logger.error("分页查询完成订单信息失败", e);
            result.setResultEnum(ResultEnum.RESULT_ORDER_QUERY_ERROR);
        }
        return  result;
    }
    @RequestMapping(value = "/notDisOrderPage")
    public ResponseResult<PageInfo<TbOrderVo>> queryNotDisOrderPageList(@RequestBody QueryOrder queryOrder){
        ResponseResult<PageInfo<TbOrderVo>> result = new ResponseResult<>();
        try{
            PageInfo<TbOrderVo> pageOrder = orderFacade.queryNotDisOrderPageList(queryOrder);
            result.setData(pageOrder);
        } catch(OperationException e){
            logger.error("分页查询订单信息失败", e);
            result.setResultEnum(ResultEnum.RESULT_ORDER_QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping("/info/{id}")
    public ResponseResult<TbOrderVo> queryOrderInfo(@PathVariable("id")Long orderID){
        ResponseResult<TbOrderVo> result = new ResponseResult<>();
        try{
            TbOrderVo orderVo = orderFacade.queryOrderInfo(orderID);
            result.setData(orderVo);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch(OperationException e) {
            logger.error("查询订单详细信息失败", e);
            result.setResultEnum(ResultEnum.RESULT_ORDER_QUERY_ERROR);
        }
        return result;
    }


    @RequestMapping(value = "/dispatch", method = RequestMethod.POST)
    public ResponseResult<?> dispatchStaffOrder(@RequestBody TbOrderVo orderVo){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            orderFacade.dispatchOrder(orderVo);
        } catch (OperationException e) {
            logger.error("订单分派异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_DISPATCH_ORDER_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public ResponseResult<?> finishedOrder(@RequestBody TbFinishedOrderVo finishedOrderVo){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            orderFacade.finishOrder(finishedOrderVo);
        } catch (OperationException e) {
            logger.error("订单结束异常:{}",e);
            result.setResultEnum(RESULT_ORDER_FINISHED_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/cancelOrder/{orderId}", method = RequestMethod.GET)
    public ResponseResult<?> cancelOrder(@PathVariable("orderId")Long orderId){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            orderFacade.cancelOrder(orderId);
        } catch (OperationException e){
            logger.error("下单取消异常:{}",e);
            result.setResultEnum(RESULT_ORDER_CANCEL_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/againOrder/{orderId}", method = RequestMethod.GET)
    public ResponseResult<?> againDisOrder(@PathVariable("orderId")Long orderId){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            orderFacade.againOrder(orderId);
        } catch (OperationException e){
            logger.error("下单重新分派异常:{}",e);
            result.setResultEnum(RESULT_ORDER_AGAIN_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "resetOrder/{orderId}", method = RequestMethod.GET)
    public ResponseResult<?> resetDisOrder(@PathVariable("orderId")Long orderId){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            orderFacade.resetDisOrder(orderId);
        } catch (OperationException e){
            logger.error("重新下发订单异常:{}",e);
            result.setResultEnum(RESULT_ORDER_RESET_ERROR);
        }
        return result;
    }

    //财务统计
    @RequestMapping(value = "/checkOrder/{orderId}", method = RequestMethod.GET)
    public ResponseResult<?> checkOrder(@PathVariable("orderId")Long orderId){
        ResponseResult<?> result = new ResponseResult<>();
        try {
          orderFacade.checkDisOrder(orderId);
        } catch (Exception e){
            logger.error("重新下发订单异常:{}",e);
            result.setResultEnum(RESULT_ORDER_RESET_ERROR);
        }
        return result;
    }
    //财务驳回
    @RequestMapping(value = "/callBackOrder/{orderId}", method = RequestMethod.GET)
    public ResponseResult<?> callBackOrder(@PathVariable("orderId")Long orderId){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            orderFacade.callBackOrder(orderId);
        } catch (Exception e){
            logger.error("重新下发订单异常:{}",e);
            result.setResultEnum(RESULT_ORDER_RESET_ERROR);
        }
        return result;
    }
    //
    @RequestMapping(value = "/selectOrderNumber", method = RequestMethod.GET)
    public List<Map<String,String>> callBackOrder(){
        List<Map<String,String>> result = new ArrayList<>();
        try {
            result=   orderFacade.selectOrderNumber();
        } catch (Exception e){
            logger.error("重新下发订单异常:{}",e);

        }
        return result;
    }

    @RequestMapping(value = "/selectOrderFinishNumber", method = RequestMethod.GET)
    public List<Map<String,Integer>> selectOrderFinishNumber(){
        List<Map<String,Integer>> result = new ArrayList<>();
        try {
            result=   orderFacade.selectOrderFinishNumber();
        } catch (Exception e){
            logger.error("重新下发订单异常:{}",e);

        }
        return result;
    }
    @RequestMapping(value = "/selectFirstOrderFinishNumber", method = RequestMethod.GET)
    public List<Map<String,Integer>> selectFirstOrderFinishNumber(){
        List<Map<String,Integer>> result = new ArrayList<>();
        try {
            result=   orderFacade.selectFirstOrderFinishNumber();
        } catch (Exception e){
            logger.error("重新下发订单异常:{}",e);

        }
        return result;
    }
    @RequestMapping(value = "/selectSevenOrderNumber", method = RequestMethod.GET)
    public List<Map<String,Integer>> selectSevenOrderNumber(){
        List<Map<String,Integer>> result = new ArrayList<>();
        try {
            result=   orderFacade.selectSevenOrderNumber();
        } catch (Exception e){
            logger.error("重新下发订单异常:{}",e);

        }
        return result;
    }
    @RequestMapping(value = "/selectSevenOrderNumber2", method = RequestMethod.GET)
    public List<Map<String,String>> selectSevenOrderNumber2(){
        List<Map<String,String>> result = new ArrayList<>();
        try {
            result=   orderFacade.selectSevenOrderNumber2();
        } catch (Exception e){
            logger.error("重新下发订单异常:{}",e);

        }
        return result;
    }
    @RequestMapping(value = "/selectOrinigOrderNumber", method = RequestMethod.GET)
    public List<Map<String,String>> selectOrinigOrderNumber(){
        List<Map<String,String>> result = new ArrayList<>();
        try {
            result=   orderFacade.selectOrinigOrderNumber();
        } catch (Exception e){
            logger.error("重新下发订单异常:{}",e);

        }
        return result;
    }
}
