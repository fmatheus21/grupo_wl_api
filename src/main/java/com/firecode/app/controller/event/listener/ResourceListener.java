package com.firecode.app.controller.event.listener;

import com.firecode.app.controller.event.ResourceEvent;
import java.net.URI;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class ResourceListener implements ApplicationListener<ResourceEvent> {

    /* captura o evento e adiciona header location */
    @Override
    public void onApplicationEvent(ResourceEvent event) {
        HttpServletResponse response = event.getResponse();
        Integer id = event.getId();
        this.addHeaderLocation(response, id);
    }

    private void addHeaderLocation(HttpServletResponse response, Integer id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }

}
