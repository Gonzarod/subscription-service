Feature:  User can view subscriptions
  Scenario: User Student successfully view subscriptions
    Given Student with a username "jesus.student"
    When go to subscription option and call to api is made
    Then response status is 200
    And all subscription data is retrieved

  Scenario: User Teacher successfully view subscriptions
    Given Teacher with a username "albert.teacher"
    When go to subscription option and call to api is made
    Then response status is 200
    And all subscription data is retrieved

  Scenario: Admin successfully view subscriptions
    Given Admin with username "jose.admin"
    When go to subscription option and call to api is made
    Then response status is 200
    And all subscription data is retrieved