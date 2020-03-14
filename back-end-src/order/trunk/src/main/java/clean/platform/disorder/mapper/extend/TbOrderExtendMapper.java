/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.mapper.extend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import clean.platform.disorder.model.entity.TbFinishedOrder;
import clean.platform.disorder.model.entity.TbOrder;
import clean.platform.disorder.model.entity.vo.JudgeFinishedOrderVo;
import clean.platform.disorder.model.entity.vo.TbStaffVo;
import org.apache.ibatis.annotations.Param;

import clean.platform.disorder.model.entity.vo.TbOrderVo;
import org.springframework.stereotype.Repository;

@Repository
public interface TbOrderExtendMapper {

    List<TbOrderVo> selectByVo(TbOrderVo record);

    List<TbOrderVo> selectOrderByVo(TbOrderVo record);

    List<TbOrderVo> selectNotDisOrderByVo(TbOrderVo record);

    TbOrderVo selectByPK(@Param("id") Long id);

    List<JudgeFinishedOrderVo> selectJudgeFinsihOrder(JudgeFinishedOrderVo judgeFinishedOrderVo);

    /**
     * @param id
     * @param status
     */
    void updateOrderStatus(@Param("id") Long id, @Param("status") Short status);

    void updateFinishOrderStatus(@Param("id") Long id, @Param("status") Short status);

    List<TbStaffVo> selectOrderStaff(@Param("orderId") Long orderId);

    List<Map<Integer, Integer>> selectOrderNumber();

    List<Map<String, Integer>> selectOrderFinishNumber();

    List<Map<String, Integer>> selectFirstOrderFinishNumber();

    List<Map<String, Integer>> selectSevenOrderNumber();

    List<Map<String, String>> selectSevenOrderNumber2();

    List<Map<Integer, Integer>> selectOrinigOrderNumber();

}
