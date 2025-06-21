/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.supermercado.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.convert.Converter;
import com.supermercado.entity.Fornecedor;
import com.supermercado.service.FornecedorService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

/**
 *
 * @author Felipe
 */
@FacesConverter(forClass = Fornecedor.class, managed = true)
public class FornecedorConverter implements Converter<Fornecedor> {

    @Inject
    private FornecedorService fornecedorService;

    @Override
    public Fornecedor getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("FornecedorConverter.getAsObject: value=" + value);
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            Fornecedor f = fornecedorService.findById(Fornecedor.class, id).orElse(null);
            System.out.println("FornecedorConverter.getAsObject: found=" + f);
            return f;
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "ID inválido."));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Fornecedor fornecedor) {
        if (fornecedor == null || fornecedor.getId() == null) {
            return "";
        }

        return fornecedor.getId().toString();
    }
}
