package pl.com.laweta.controller;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import pl.com.laweta.dto.MailDto;
import pl.com.laweta.service.MailService;

@Path("/api/mail")
public class MailController {

    private final Validator validator;
    private final MailService mailService;

    @Inject
    public MailController(MailService mailService, Validator validator) {
        this.mailService = mailService;
        this.validator = validator;
    }

    @POST
    @Path("/send")
    public Response sendEmail(@RequestBody MailDto mailDto) {
        if (!validator.validate(mailDto).isEmpty()) {
            return Response.status(Status.BAD_REQUEST).build();
        }

        mailService.sendEmail(mailDto);
        return Response.ok().build();
    }
}