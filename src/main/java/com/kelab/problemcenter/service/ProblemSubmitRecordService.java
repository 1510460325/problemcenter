package com.kelab.problemcenter.service;

import com.kelab.info.base.PaginationResult;
import com.kelab.info.base.SingleResult;
import com.kelab.info.context.Context;
import com.kelab.info.problemcenter.info.ProblemSubmitRecordInfo;
import com.kelab.info.problemcenter.query.ProblemSubmitRecordQuery;
import com.kelab.problemcenter.dal.domain.ProblemSubmitRecordDomain;
import com.kelab.problemcenter.result.SubmitResult;

public interface ProblemSubmitRecordService {


    /**
     * 分页查询
     */
    PaginationResult<ProblemSubmitRecordInfo> queryPage(Context context, ProblemSubmitRecordQuery query);

    /**
     * 提交判题， 返回提交结果
     */
    SubmitResult submit(Context context, ProblemSubmitRecordDomain record);

    /**
     * 获取具体提交信息
     */
    SingleResult<ProblemSubmitRecordInfo> querySubmitDetail(Context context, Integer submitId);

    /**
     * 累计判题个数
     */
    Integer judgeCount(Context context);
}
