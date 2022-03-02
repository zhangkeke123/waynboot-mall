package com.wayn.mobile.api.controller.message;

import com.wayn.common.core.domain.tool.EmailConfig;
import com.wayn.common.core.domain.vo.SendMailVO;
import com.wayn.common.core.service.tool.IMailConfigService;
import com.wayn.common.util.R;
import com.wayn.common.util.mail.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("message/email")
public class EmailController {

    @Autowired
    private IMailConfigService mailConfigService;

    @PostMapping
    public R sendEmail(String subject, String content, String tos) {
        EmailConfig emailConfig = mailConfigService.getById(1L);
        SendMailVO sendMailVO = new SendMailVO();
        sendMailVO.setSubject(subject);
        sendMailVO.setContent(content);
        sendMailVO.setTos(List.of(tos));
        MailUtil.sendMail(emailConfig, sendMailVO, false);
        return R.success();
    }
}
