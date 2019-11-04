package com.barbosa.dev.expensecontrol.controller;

import com.barbosa.dev.expensecontrol.service.AbstractService;
import com.barbosa.dev.expensecontrol.util.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.barbosa.dev.expensecontrol.view.PaginationView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

public abstract class AbstractController<T extends BaseEntity<Long>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    private final AbstractService<T> service;

    private final String VIEWS_BASE_PATH;
    private final String REQUEST_MAPPING_PATH;

    @Autowired
    private PaginationView paginationView;

    @Autowired
    public AbstractController(AbstractService<T> service, String VIEWS_BASE_PATH, String REQUEST_MAPPING_PATH) {
        this.service = service;
        this.VIEWS_BASE_PATH = VIEWS_BASE_PATH;
        this.REQUEST_MAPPING_PATH = REQUEST_MAPPING_PATH;
    }

    @GetMapping
    public String listEntities(ModelMap model,
                               @RequestParam(value = "pageSize", required = false) Optional<Integer> pageSize,
                               @RequestParam(value = "page", required = false) Optional<Integer> page) {
        Page<T> results = service.findAll(paginationView.getPositionPagination(pageSize, page));
        model.put("entities", results);
        model.put("selectedPageSize", paginationView.getEvalPageSize());
        model.put("pageSizes", paginationView.getPAGE_SIZES());
        model.put("pager", paginationView.getPagerView(results.getTotalPages(), results.getNumber()));
        return VIEWS_BASE_PATH + "/list";
    }

    @GetMapping(value = "/new")
    public String initCreationForm(ModelMap modelMap) {
        T entity = getEntity();
        modelMap.put("entity", entity);
        return VIEWS_BASE_PATH + "/form";
    }

    @PostMapping(value = "/new")
    public String processCreationForm(@ModelAttribute("entity") @Valid T entity, BindingResult result, ModelMap model, @RequestParam(required = false) MultipartFile file) {
        LOGGER.debug("Received request to create the {}", entity);
        if (result.hasErrors()) {
            LOGGER.debug("Validation errors occurred in the process to create the entity {}", result.getAllErrors());
            model.put("entity", entity);
            return VIEWS_BASE_PATH + "/form";
        } else {
            this.service.save(entity);
            return "redirect:" + REQUEST_MAPPING_PATH + "/" + entity.getId();
        }
    }

    @GetMapping("/{entityId}/edit")
    public String initUpdateForm(@PathVariable("entityId") Long entityId, ModelMap model) {
        LOGGER.debug("Received request to edit a entity by its id: {}", entityId);
        T entity = this.service.findById(entityId);
        LOGGER.debug("Received request to edit the {}", entity);
        model.put("entity", entity);
        return VIEWS_BASE_PATH + "/form";
    }

    @PostMapping("/{entityId}/edit")
    public String processUpdateForm(@ModelAttribute @Valid T entity, BindingResult result, @PathVariable("entityId") Long entityId, ModelMap model, @RequestParam(required = false) MultipartFile file) {
        LOGGER.debug("Received request to update the {}", entity);
        if (result.hasErrors()) {
            LOGGER.debug("Validation errors occurred in the process of update the entity {}", result.getAllErrors());
            model.put("entity", entity);
            return VIEWS_BASE_PATH + "/form";
        } else {
            entity.setId(entityId);
            this.service.save(entity);
            return "redirect:" + REQUEST_MAPPING_PATH + "/{entityId}";
        }
    }

    @GetMapping("/{entityId}/delete")
    public String delete(@PathVariable("entityId") Long entityId) {
        LOGGER.debug("Received request to delete a entity by its id: {}", entityId);
        this.service.deleteById(entityId);
        return "redirect:" + REQUEST_MAPPING_PATH;
    }

    @GetMapping("/{entityId}")
    public ModelAndView show(@PathVariable("entityId") Long entityId, ModelMap modelMap) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(VIEWS_BASE_PATH + "/details");
        mav.addAllObjects(modelMap);
        mav.addObject("entity", this.service.findById(entityId));
        return mav;
    }

    protected abstract T getEntity();
}
