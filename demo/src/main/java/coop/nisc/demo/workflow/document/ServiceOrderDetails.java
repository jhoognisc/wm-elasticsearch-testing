package coop.nisc.demo.workflow.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrderDetails {

    public SourceType type;

    public String sourceNumber;

    public String generalComments;

    public String serviceComments;

    public String customerName;

    public String contactName;

    public String phoneNumber;

    public Address address;
}
