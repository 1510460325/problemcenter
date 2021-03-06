package com.kelab.problemcenter.convert;

import com.kelab.info.problemcenter.info.ProblemUserMarkInfo;
import com.kelab.info.problemcenter.info.ProblemUserMarkInnerInfo;
import com.kelab.problemcenter.constant.enums.MarkType;
import com.kelab.problemcenter.dal.domain.ProblemUserMarkDomain;
import com.kelab.problemcenter.dal.model.ProblemUseMarkModel;
import org.springframework.beans.BeanUtils;

public class ProblemUserMarkConvert {

    public static ProblemUserMarkDomain modelToDomain(ProblemUseMarkModel model) {
        if (model == null) {
            return null;
        }
        ProblemUserMarkDomain domain = new ProblemUserMarkDomain();
        BeanUtils.copyProperties(model, domain);
        domain.setMarkType(MarkType.valueOf(model.getMarkType()));
        return domain;
    }

    public static ProblemUserMarkInfo domainToInfo(ProblemUserMarkDomain domain) {
        if (domain == null) {
            return null;
        }
        ProblemUserMarkInfo info = new ProblemUserMarkInfo();
        BeanUtils.copyProperties(domain, info);
        return info;
    }

    public static ProblemUseMarkModel domainToModel(ProblemUserMarkDomain domain) {
        if (domain == null) {
            return null;
        }
        ProblemUseMarkModel model = new ProblemUseMarkModel();
        BeanUtils.copyProperties(domain, model);
        model.setMarkType(domain.getMarkType().value());
        return model;
    }

    public static ProblemUserMarkInnerInfo domainToInnerInfo(ProblemUserMarkDomain domain) {
        if (domain == null) {
            return null;
        }
        ProblemUserMarkInnerInfo info = new ProblemUserMarkInnerInfo();
        BeanUtils.copyProperties(domain, info);
        return info;
    }

}
