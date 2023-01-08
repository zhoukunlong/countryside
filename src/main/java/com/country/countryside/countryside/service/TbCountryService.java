package com.country.countryside.countryside.service;

import com.country.countryside.countryside.vo.CountryInVo;

/**
 *
 */
public interface TbCountryService {

    /**
     * 添加村庄
     * @param inVo
     */
    void addCountry(CountryInVo inVo);

    /**
     * 申请加入村庄
     * @param userId
     * @param countryId
     */
    void joinCountry(String userId, String countryId);

    /**
     * 审批工单
     * @param id
     */
    void approve(String id, Integer status);
}
