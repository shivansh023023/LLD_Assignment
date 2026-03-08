package com.example.reports;

/**
 * TODO (student):
 * Implement Proxy responsibilities here:
 * - access check
 * - lazy loading
 * - caching of RealReport within the same proxy
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    
    // Lazy-loaded real subject
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // 1. Access Check
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: User '" + user.getName() + 
                               "' does not have permission to view " + classification + " reports.");
            return;
        }
        
        // 2. Lazy Loading & Caching
        if (realReport == null) {
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] using cached instance for report " + reportId);
        }
        
        // 3. Delegate to Real Subject
        realReport.display(user);
    }
}
