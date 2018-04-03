package com.sg.service;

import com.sg.dao.EmailContactDao;

public class EmailListServiceImpl implements EmailListService {

    private EmailContactDao dao;

    public EmailListServiceImpl(EmailContactDao dao) {
        this.dao = dao;
    }
}
