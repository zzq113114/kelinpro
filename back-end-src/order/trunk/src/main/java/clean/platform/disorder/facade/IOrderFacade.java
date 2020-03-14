package clean.platform.disorder.facade;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.model.dto.OrderVo;
import clean.platform.disorder.model.entity.TbFinishedOrder;
import clean.platform.disorder.model.entity.TbOrder;
import clean.platform.disorder.model.entity.vo.JudgeFinishedOrderVo;
import clean.platform.disorder.model.entity.vo.TbFinishedOrderVo;
import clean.platform.disorder.model.entity.vo.TbOrderVo;
import clean.platform.disorder.model.query.QueryOrder;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单业务接口
 */

public interface IOrderFacade {

    /**
     * 增加订单
     *
     * @param orderVo
     * @throws OperationException
     */
    public void addOrder(OrderVo orderVo) throws OperationException;

    /**
     * 修改订单
     *
     * @param orderVo
     * @throws OperationException
     */
    public void updateOrder(OrderVo orderVo) throws OperationException;

    /**
     * 删除订单
     *
     * @param id
     * @throws OperationException
     */
    public void deleteOrder(Long id) throws OperationException;

    /**
     * 查询订单详情
     *
     * @param id
     * @return
     * @throws OperationException
     */
    public TbOrderVo queryOrderInfo(Long id) throws OperationException;

    /**
     * 根据条件查询订单列表
     *
     * @param queryOrder
     * @return
     * @throws OperationException
     */
    public PageInfo<TbOrderVo> queryOrderPageList(QueryOrder queryOrder) throws OperationException;

    /**
     * 查询未下发的派单
     *
     * @param queryOrder
     * @return
     * @throws OperationException
     */
    public PageInfo<TbOrderVo> queryNotDisOrderPageList(QueryOrder queryOrder) throws OperationException;

    /**
     * 分派订单
     *
     * @param orderVo
     * @throws OperationException
     */
    void dispatchOrder(TbOrderVo orderVo) throws OperationException;

    /**
     * 结束订单
     *
     * @param finishedOrderVo
     * @throws OperationException
     */
    void finishOrder(TbFinishedOrderVo finishedOrderVo) throws OperationException;

    /**
     * 取消下单
     *
     * @param orderId
     * @throws OperationException
     */
    void cancelOrder(Long orderId) throws OperationException;

    /**
     * 重新派单
     *
     * @param orderId
     * @throws OperationException
     */
    void againOrder(Long orderId) throws OperationException;

    /**
     * 重新分派订单
     *
     * @param orderId
     * @throws OperationException
     */
    void resetDisOrder(Long orderId) throws OperationException;

    /**
     * 订单审核   2020-1-29
     *
     * @param orderId
     * @throws OperationException
     */
    void checkDisOrder(Long orderId) throws OperationException;

    /**
     * 订单审核   2020-1-29
     *
     * @param orderId
     * @throws OperationException
     */
    void callBackOrder(Long orderId) throws OperationException;

    /**
     * 订单审核查看功能   2020-1-29
     *
     * @param
     * @throws OperationException
     */
    PageInfo<JudgeFinishedOrderVo> judgeFinishOrder(QueryOrder queryOrder) throws OperationException;

    List<Map<String, String>> selectOrderNumber() throws OperationException;

    List<Map<String, Integer>> selectOrderFinishNumber() throws OperationException;

    List<Map<String, Integer>> selectFirstOrderFinishNumber() throws OperationException;

    List<Map<String, Integer>> selectSevenOrderNumber() throws OperationException;

    List<Map<String, String>> selectSevenOrderNumber2() throws OperationException;

    List<Map<String, String>> selectOrinigOrderNumber() throws OperationException;
}
