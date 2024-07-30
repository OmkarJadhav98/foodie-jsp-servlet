package src.main.java.com.onlinefoodorderingsystem.servlet;

import com.onlinefoodorderingsystem.model.Contact;
import com.onlinefoodorderingsystem.model.Address;
import com.onlinefoodorderingsystem.service.ContactService;
import com.onlinefoodorderingsystem.service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
    private ContactService contactService;
    private AddressService addressService;

    @Override
    public void init() throws ServletException {
        contactService = new ContactService();
        addressService = new AddressService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("getContact".equals(action)) {
            long contactId = Long.parseLong(req.getParameter("id"));
            Contact contact = contactService.getContactById(contactId);
            if (contact != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(contact.toJson());
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Contact not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("createContact".equals(action)) {
            // Extract Contact details from request
            long id = Long.parseLong(req.getParameter("id"));
            long phone = Long.parseLong(req.getParameter("phone"));
            long addressId = Long.parseLong(req.getParameter("addressId"));
            Address address = addressService.getAddressById(addressId);

            if (address != null) {
                Contact contact = new Contact(id, phone, address);
                contactService.createContact(contact);
                resp.setContentType("application/json");
                resp.getWriter().write("{\"message\":\"Contact created successfully\"}");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Address not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Implement update logic
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Implement delete logic
    }
}
