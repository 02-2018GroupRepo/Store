package bootcamp

import spock.lang.Specification

class LowProductSpec extends Specification {
    def "low products trigger an invetory request"() {
        given: "an inventory with low stock"
        Map<Integer, Integer> inv = new HashMap<Integer, Integer>(1)
        and: "the map contains a low product"
        inv.put(3, 1)
        when: "amount is lower than two"
        checkInventory(inv)
        then: "a request is sent to vedors"
        1 >9
    }
}