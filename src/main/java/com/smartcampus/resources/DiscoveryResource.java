/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author anudhi
 */

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {
    private static String BASE = "http://localhost:8080/api/v1";

    @GET
    public Response discover() {
        Map<String, Object> meta = new LinkedHashMap<>();
        meta.put("service", "Smart Campus IoT Sensor Management API");
        meta.put("version", "1.0.0");
        meta.put("status", "operational");
        meta.put("admin_contact", "facilities@smartcampus.ac.uk");

        Map<String, String> links = new LinkedHashMap<>();
        links.put("self",    BASE);
        links.put("rooms",   BASE + "/rooms");
        links.put("sensors", BASE + "/sensors");
        meta.put("_links", links);

        return Response.ok(meta).build();
    }
}