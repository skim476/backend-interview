package ai.brace;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Employee {
    public String firstName;
    public String middleInitial;
    public String lastName;
    public String socialSecurityNumber;

    public Employee(String _lastName, String _firstName, String _middleInitial, String _socialSecurityNumber) {
        firstName = _firstName;
        middleInitial = _middleInitial;
        lastName = _lastName;
        socialSecurityNumber = _socialSecurityNumber;
    }

    /**
     * need to implement hashCode() and equals() so that the 2 employees with the same name are considered the same key
     * in the Map
     *
     * @return
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(firstName).append(middleInitial).append(lastName).append(socialSecurityNumber).toHashCode();
    }

    /**
     * need to implement hashCode() and equals() so that the 2 employees with the same name are considered the same key
     * in the Map
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Employee)) {
            return false;
        }

        Employee other = (Employee) o;
        return new EqualsBuilder().append(firstName, other.firstName).append(lastName, other.lastName).append(middleInitial, other.middleInitial).append(socialSecurityNumber, other.socialSecurityNumber).isEquals();
    }
}
