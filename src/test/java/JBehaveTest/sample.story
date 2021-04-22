Scenario: ADD function
Given numbers i and j
When we add them
Then verify the sum

Scenario: SUBTRACT function
Given numbers i and j
When we subtract i from j
Then verify the difference

Scenario: increase random number
Given a counter
And the counter has any integral value
When the user increases the counter
Then the value of the counter must be 1 greater than previous value