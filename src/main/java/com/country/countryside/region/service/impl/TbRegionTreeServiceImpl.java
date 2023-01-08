package com.country.countryside.region.service.impl;

import com.country.countryside.common.CommonConstants;
import com.country.countryside.countryside.mapper.TbCountryMapper;
import com.country.countryside.region.bean.TbRegionTree;
import com.country.countryside.region.mapper.TbRegionTreeMapper;
import com.country.countryside.region.service.TbRegionTreeService;
import com.country.countryside.region.vo.RegionInVo;
import com.country.countryside.region.vo.RegionTreesOutVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 地市级联业务操作类
 * @author zhoukunlong
 * @date 2023/01/07
 */
@Service
public class TbRegionTreeServiceImpl implements TbRegionTreeService {

    @Resource
    private TbRegionTreeMapper tbRegionTreeMapper;
    @Resource
    private TbCountryMapper tbCountryMapper;

    /**
     * 添加地市信息
     * @param inVo
     */
    @Override
    @Transactional
    public void addRegion(RegionInVo inVo) {
        TbRegionTree tbRegionTree = new TbRegionTree();
        BeanUtils.copyProperties(inVo,tbRegionTree);
        tbRegionTree.setCreateTime(CommonConstants.format.format(new Date()));
        tbRegionTree.setUpdateTime(CommonConstants.format.format(new Date()));
        tbRegionTree.setIsDelete(CommonConstants.Delete.NO);
        tbRegionTree.setIsLeaf(CommonConstants.Leaf.YES);
        TbRegionTree parentRegion = tbRegionTreeMapper.findById(inVo.getParentId());
        tbRegionTree.setLayer(parentRegion.getLayer()+1);
        if(parentRegion.getIsLeaf() == CommonConstants.Leaf.YES){
            parentRegion.setIsLeaf(CommonConstants.Leaf.NO);
            parentRegion.setUpdateTime(CommonConstants.format.format(new Date()));
            tbRegionTreeMapper.updateRegion(parentRegion);
        }
        tbRegionTreeMapper.addRegion(tbRegionTree);
    }

    /**
     * 查询地市级联信息树
     * @return
     */
    @Override
    public List<RegionTreesOutVo> findRegionTrees() {
        List<TbRegionTree> trees = tbRegionTreeMapper.findByParentId("0");
        return createTree(trees.get(0).getId());
    }

    /**
     * 创建树
     */
    private List<RegionTreesOutVo> createTree(String id){
        List<TbRegionTree> trees = tbRegionTreeMapper.findByParentId(id);
        List<RegionTreesOutVo> outVos = new ArrayList<>();
        trees.forEach((outVo) -> {
            RegionTreesOutVo vo = new RegionTreesOutVo();
            BeanUtils.copyProperties(outVo,vo);
            List<RegionTreesOutVo> vos = createTree(vo.getId());
            vo.setChilds(vos);
            if(vo.getIsLeaf() == CommonConstants.Leaf.YES){
                vo.setCount(tbCountryMapper.findCountByGroupId(vo.getId()));
            }else {
                vo.setCount(vos.stream().mapToInt(RegionTreesOutVo::getCount).sum());
            }
            outVos.add(vo);
        });
        return outVos;
    }
}
