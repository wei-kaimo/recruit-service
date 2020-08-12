package com.system.recruit.service;

import com.system.recruit.entity.HrPosition;
import com.system.recruit.entity.info.*;

import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 19:53
 */
public interface PositionDataService {
    List<HrPosition> getAllPosition(GetAllPositionReq getAllPositionReq);

    Map<String, Object> addPosition(AddPositionReq addPositionReq);

    Map<String, Object> updatePosition(UpdatePositionReq updatePositionReq);

    Map<String, Object> deletePosition(DeletePositionReq deletePositionReq);

    GetPositionListResp getPositionList(GetPositionListReq getPositionListReq);

    HrPosition getPositionById(GetPositionByIdReq getAllPositionReq);
}
