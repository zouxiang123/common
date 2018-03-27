<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head_standard.jsp"></jsp:include>
<title>营养评估</title>
</head>
<body>
<form action="#" id="nuAssessmentForm">
    <input type="hidden" name="id" value="${record.id }"/>
    <input type="hidden" name="fkPatientId" value="${patient.id }"/>
    <input type="hidden" name="score" value="${record.score }"/>
<div class="new-assess">
    <div class="new-assess-card">
        <div class="head">评估时间</div>
        <div class="content">
            <div class="list mt-8 mb-8">
                <div class="u-xt-6">本次时间：<input type="text" name="recordDateShow" value="${record.recordDateShow }" id="recordDateInput" readonly="readonly"/>
                </div>
                <div class="u-xt-6">下次时间：<input type="text" name="nextDateShow" value="${record.nextDateShow }" id="nextDateInput" readonly="readonly"/>
                </div>
            </div>
        </div>
    </div>
    <div class="new-assess-card">
        <div class="head">主观综合营养评估（SGA）</div>
        <div class="content">
            <div class="list mt-8">
                <div class="u-xt-6 fw-bold"> 体重改变：</div>
                <div class="u-xt-6 fw-bold"> 饮食改变：</div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">1分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaWeightChange" value="01" data-score="1">
                        <span class="icon-radio"></span>无/增加
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">1分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaMetatrophia" value="01" data-score="1">
                        <span class="icon-radio"></span>进食固体物质且量无减少
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">2分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaWeightChange" value="02" data-score="2">
                        <span class="icon-radio"></span>减少&lt;5%
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">2分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaMetatrophia" value="02" data-score="2">
                        <span class="icon-radio"></span>固体食物进食不足
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">3分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaWeightChange" value="03" data-score="3">
                        <span class="icon-radio"></span>减少5%-10%
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">3分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaMetatrophia" value="03" data-score="3">
                        <span class="icon-radio"></span>进食流食或食量中度减少
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">4分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaWeightChange" value="04" data-score="4">
                        <span class="icon-radio"></span>减少10%-15%
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">4分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaMetatrophia" value="04" data-score="4">
                        <span class="icon-radio"></span>进食少量低能量流食
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">5分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaWeightChange" value="05" data-score="5">
                        <span class="icon-radio"></span>减少1>15%
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">5分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaMetatrophia" value="05" data-score="5">
                        <span class="icon-radio"></span>几乎无法进食
                    </label>
                </div>
            </div>

            <div class="list mt-8">
                <div class="u-xt-6 fw-bold">肠胃症状：</div>
                <div class="u-xt-6 fw-bold">活动能力：</div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">1分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaIasSymptom" value="01" data-score="1">
                        <span class="icon-radio"></span>无症状
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">1分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaActivity" value="01" data-score="1">
                        <span class="icon-radio"></span>完全不受限或较前明显改善
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">2分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaIasSymptom" value="02" data-score="2">
                        <span class="icon-radio"></span>恶心
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">2分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaActivity" value="02" data-score="2">
                        <span class="icon-radio"></span>轻、中度受限
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">3分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaIasSymptom" value="03" data-score="3">
                        <span class="icon-radio"></span>呕吐或其它中度胃肠道症状
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">3分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaActivity" value="03" data-score="3">
                        <span class="icon-radio"></span>日常生活受限
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">4分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaIasSymptom" value="04" data-score="4">
                        <span class="icon-radio"></span>腹泻
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">4分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaActivity" value="04" data-score="4">
                        <span class="icon-radio"></span>仅能从事很轻度体力活动
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">5分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaIasSymptom" value="05" data-score="5">
                        <span class="icon-radio"></span>严重厌食
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">5分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaActivity" value="05" data-score="5">
                        <span class="icon-radio"></span>卧床或仅坐轮椅少量活动
                    </label>
                </div>
            </div>

            <div class="list mt-8">
                <div class="u-xt-6 fw-bold">并发症：</div>
                <div class="u-xt-6 fw-bold">脂肪储备/皮下脂肪：</div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">1分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaComplication" value="01" data-score="1">
                        <span class="icon-radio"></span>透析&lt;12个月无并发症
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">1分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaFat" value="01" data-score="1">
                        <span class="icon-radio"></span>没有改变
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">2分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaComplication" value="02" data-score="2">
                        <span class="icon-radio"></span>1-2年或轻度并发症
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">2分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaFat" value="02" data-score="2">
                        <span class="icon-radio"></span>介于二者间
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">3分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaComplication" value="03" data-score="3">
                        <span class="icon-radio"></span>2-4年或>75岁或中度并发症
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">3分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaFat" value="03" data-score="3">
                        <span class="icon-radio"></span>中度减少
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">4分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaComplication" value="04" data-score="4">
                        <span class="icon-radio"></span>>4年或重度并发症
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">4分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaFat" value="04" data-score="4">
                        <span class="icon-radio"></span>介于二者间
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">5分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaComplication" value="05" data-score="5">
                        <span class="icon-radio"></span>严重多种并发症
                    </label>
                </div>
                <div class="u-xt-6">
                    <span class="fc-black_5">5分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaFat" value="05" data-score="5">
                        <span class="icon-radio"></span>严重减少
                    </label>
                </div>
            </div>
            <div class="list mt-8">
                <div class="u-xt-6 fw-bold">肌肉消耗：</div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">1分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaMuscleConsume" value="01" data-score="1">
                        <span class="icon-radio"></span>没有改变
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">2分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaMuscleConsume" value="02" data-score="2">
                        <span class="icon-radio"></span>介于二者间
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">3分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaMuscleConsume" value="03" data-score="3">
                        <span class="icon-radio"></span>中度消耗
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">4分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaMuscleConsume" value="04" data-score="4">
                        <span class="icon-radio"></span>介于二者间
                    </label>
                </div>
            </div>
            <div class="list">
                <div class="u-xt-6">
                    <span class="fc-black_5">5分</span>
                    <label class="u-radio ml-20">
                        <input type="radio" name="sgaMuscleConsume" value="05" data-score="5">
                        <span class="icon-radio"></span>严重消耗
                    </label>
                </div>
            </div>
        </div>
        <div class="footer pb-12">
            <div class="u-list">
                SGA 评分结果：
                <span class="fs-28 fc-red" id="scoreSpan">${record.score }</span>
                <i class="fc-black_5">分</i>
            </div>
            <div class="fc-black_5">说明：改良法SGA 评估方法、分值。分值越高，代表营养越差。营养正常：7 分；轻－中度营养不良：8-15 分；重度营养不良：≥ 16 分。</div>
        </div>
    </div>
</div>
</form>
<script type="text/javascript" src="${ctx }/assets/js/nutrition/assessment_edit.js?version=${version}"></script>
</body>
</html>

