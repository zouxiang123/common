package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.Feedback;

@Repository
public interface FeedbackMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table feedback
     * 
     * @mbggenerated Fri Nov 13 14:01:54 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table feedback
     * 
     * @mbggenerated Fri Nov 13 14:01:54 CST 2015
     */
    int insert(Feedback record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table feedback
     * 
     * @mbggenerated Fri Nov 13 14:01:54 CST 2015
     */
    int insertSelective(Feedback record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table feedback
     * 
     * @mbggenerated Fri Nov 13 14:01:54 CST 2015
     */
    Feedback selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table feedback
     * 
     * @mbggenerated Fri Nov 13 14:01:54 CST 2015
     */
    int updateByPrimaryKeySelective(Feedback record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table feedback
     * 
     * @mbggenerated Fri Nov 13 14:01:54 CST 2015
     */
    int updateByPrimaryKey(Feedback record);
}