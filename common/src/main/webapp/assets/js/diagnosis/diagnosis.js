$(function() {

    /*
     * var menu = { "患者中心" : ctx + "/patient/center/toCenter.shtml", "诊断信息" :
     * null }; setBreadcrumb(menu);
     */
    $("#diagnosisInfoNavId").addClass("active");

    $(".tab-action2").click(function() {
        $(this).parent().parent().find(".content-editing-wrap").eq(0).toggle(400);
        if ($(this).find("img").hasClass("arrow-up")) {
            $(this).find("span").html("收起");
            $(this).parent().find(".not-selected").removeClass("not-selected").addClass("selected2");
            $(this).find("img").attr("src", ctx + "/assets/img/arrow-down.png").removeClass("arrow-up").addClass("arrow-down");
        } else {
            $(this).find("span").html("打开");
            $(this).parent().find(".selected2").removeClass("selected2").addClass("not-selected");
            $(this).find("img").attr("src", ctx + "/assets/img/arrow-right.png").removeClass("arrow-down").addClass("arrow-up");
        }
    });

    function openFirstArrow(divId) {
        var show_editing = $("#" + divId).find(".content-editing-wrap").eq(0);
        var arrow = show_editing.parent().find(".tab-action2");
        if (arrow.parent().find(".not-selected").length == 0)
            return;
        arrow.parent().find(".not-selected").removeClass("not-selected").addClass("selected2");
        arrow.find("span").html("收起");
        arrow.find("img").attr("src", ctx + "/assets/img/arrow-down.png").removeClass("arrow-up").addClass("arrow-down");
        show_editing.toggle(400);
    }

    openFirstArrow("medicalHistory");
    openFirstArrow("clinicalDiagnosis");
    openFirstArrow("pathologicDiagnosis");
    openFirstArrow("ckdAki");
    openFirstArrow("cureComplication");
    openFirstArrow("otherDiagnosis");

    // var clinicalDiagnosis = $("#clinicalDiagnosis").html();
    // var childs = $("#clinicalDiagnosis").children();
    //
    // for (var i = 0; i < childs.length; i++) {
    // // alert(childs[i].id);
    // }

    function showNav(divId) {

        $(".content-editing-item-6").each(function(index, obj) {
            if ($(this).attr("data-link") == divId) {
                $(this).children("span").addClass("active");
            } else {
                if ($(this).children("span").hasClass("active")) {
                    $(this).children("span").removeClass("active");
                }
            }
        });

        $("#medicalHistory").hide();
        $("#clinicalDiagnosis").hide();
        $("#pathologicDiagnosis").hide();
        $("#ckdAki").hide();
        $("#cureComplication").hide();
        $("#otherDiagnosis").hide();

        $("#" + divId).show();
    }

    $(".content-editing-item-6").click(function() {
        showNav($(this).attr("data-link"));
    });

    showNav("medicalHistory");

    $(":checkbox").attr("disabled", true);
    $(":radio").attr("disabled", true);

    $("#btnAddCD").bind("click", function() {
        window.location.href = "../doctor/newPatient.shtml?patientId=" + $("#patientId").val() + "&tabId=clinicalDiagnosis";
    });

    $("#btnAddPD").bind("click", function() {
        window.location.href = "../doctor/newPatient.shtml?patientId=" + $("#patientId").val() + "&tabId=pathologicDiagnosis";
    });

    $("#btnAddCA").bind("click", function() {
        window.location.href = "../doctor/newPatient.shtml?patientId=" + $("#patientId").val() + "&tabId=ckdAki";
    });
});
