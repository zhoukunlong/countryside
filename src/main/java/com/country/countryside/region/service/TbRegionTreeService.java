package com.country.countryside.region.service;

import com.country.countryside.region.vo.RegionInVo;
import com.country.countryside.region.vo.RegionTreesOutVo;

import java.util.List;

/**
 *
 * @author zhoukunlong
 * @date 2023/01/07
 * @since
 * @see
 */
public interface TbRegionTreeService {

    /**
     * 新增地市信息
     * @param inVo
     */
    void addRegion(RegionInVo inVo);

    /**
     * 查新地市级联信息树
     * @return
     */
    List<RegionTreesOutVo> findRegionTrees();
}
