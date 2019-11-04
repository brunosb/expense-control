package com.barbosa.dev.expensecontrol.controller;

import com.barbosa.dev.expensecontrol.model.Banco;
import com.barbosa.dev.expensecontrol.service.AbstractService;
import com.barbosa.dev.expensecontrol.util.UrlUtil;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(UrlUtil.BANCO)
public class BancoController extends AbstractController<Banco> {

    private static final String VIEWS_BASE_PATH = "pages/banco";

    public BancoController(AbstractService<Banco> service) {
        super(service, VIEWS_BASE_PATH, UrlUtil.BANCO);
    }

    @Override
    protected Banco getEntity() {
        return Banco.builder().build();
    }
}
