package com.omertex.support.web;

import com.omertex.support.dao.DaoException;
import com.omertex.support.domain.Inquiry;
import com.omertex.support.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/customers/{customerName}/inquiries")
public class InquiryController
{
    @Autowired
    private InquiryService inquiryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addInquiry(@ModelAttribute("inquiry") Inquiry inquiry) throws DaoException
    {
        inquiryService.create(inquiry);
        return "inquiry";
    }

    @RequestMapping("/inquiries")
    public String home(Model model)
    {
        model.addAttribute("inquiry", new Inquiry());
        return "inquiry";
    }
}
