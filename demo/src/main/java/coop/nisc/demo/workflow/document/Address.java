package coop.nisc.demo.workflow.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    public String street;

    public String city;

    public String state;

    public String zipcode;
}
