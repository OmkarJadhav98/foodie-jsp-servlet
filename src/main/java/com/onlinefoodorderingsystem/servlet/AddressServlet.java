package src.main.java.com.onlinefoodorderingsystem.servlet;

import com.onlinefoodorderingsystem.model.Address;
import com.onlinefoodorderingsystem.service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/address")
public class AddressServlet extends HttpServlet {
    private AddressService ;

    @Override
    public void init() throws ServletException {
        addressService = new AddressService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("getAddress".equals(action)) {
            long addressId = Long.parseLong(req.getParameter("id"));
            Address address = addressService.getAddressById(addressId);
            if (address != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(address.toJson());
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Address not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("createAddress".equals(action)) {
            // Extract Address details from request
            String name = req.getParameter("name");
            String flatNo = req.getParameter("flatNo");
            String buildingName = req.getParameter("buildingName");
            String street = req.getParameter("street");
            String city = req.getParameter("city");
            String pinCode = req.getParameter("pinCode");
            String state = req.getParameter("state");

            Address address = new Address(name, flatNo, buildingName, street, city, pinCode, state);
            addressService.createAddress(address);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Address created successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("updateAddress".equals(action)) {
            long addressId = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            String flatNo = req.getParameter("flatNo");
            String buildingName = req.getParameter("buildingName");
            String street = req.getParameter("street");
            String city = req.getParameter("city");
            String pinCode = req.getParameter("pinCode");
            String state = req.getParameter("state");

            Address address = new Address(addressId, name, flatNo, buildingName, street, city, pinCode, state);
            addressService.updateAddress(address);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Address updated successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("deleteAddress".equals(action)) {
            long addressId = Long.parseLong(req.getParameter("id"));
            addressService.deleteAddress(addressId);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\":\"Address deleted successfully\"}");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }
}
