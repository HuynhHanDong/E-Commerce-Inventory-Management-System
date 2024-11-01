package controller;

import DAO.ReportDAO;

public class ReportMenuController extends BaseController {
    private final ReportDAO reportDAO;

    public ReportMenuController() {
        super();
        this.reportDAO = new ReportDAO();
    }
}
