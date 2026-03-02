import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t1);

        // Demonstrate safe "mutation" through service returning new objects
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);
        
        System.out.println("\nOriginal ticket remains unchanged: " + t1);
        System.out.println("Final updated ticket: " + t3);

        // Demonstrate external mutation via leaked list reference is now IMPOSSIBLE
        List<String> tags = t1.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nSUCCESS: External tag mutation was blocked! (UnsupportedOperationException caught)");
        }
        
        System.out.println("Immutable tags: " + t1.getTags());
    }
}
