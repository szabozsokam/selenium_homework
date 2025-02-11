Preconditions:

    Java 1.8 (newer)

    Selenium 3.141.59 (newer)

    Junit5

Case 1 � Automate Purchase Process

    Open the url: https://www.saucedemo.com/inventory.html
    Create a credential.json file in test/resources folder and store: { username: performance_glitch-user, password: secret_sauce }
    Parse the credentials from credential.json and use it for the following test
    On the website type username and password and click on login button
    Add the following items to the cart: Sauce Labs Backpack, Sauce Labs Fleece Jacket
    Validate the number on the cart symbol
    Go through the checkout process
    Validate that Thank you for your order message is appearing

Case 2 � Verify error messages for mandatory fields

    Open the url: https://www.saucedemo.com/inventory.html
    Click on login button
    Validate the error message
    Login with the following credentials: standard_user / secret_sauce
    Scroll down to the bottom of the page
    Validate the footer message contains 2022 and Terms of Service

Case 3 - Rich Text Editor

    Open the url: https://onlinehtmleditor.dev
    Type the following text to the editor: Automation Test Example
    Automation text should be typed bold format
    Test text should be typed underline format
    Validate the text is appearing in the rich text editor

Case 4 - iFrame and tab handling

    Open this url http://demo.guru99.com/test/guru99home
    Locate the image inside the iframe near bottom of the page just above Email Submission and click on it
    Verify new page is loaded in new tab with title: Selenium Live Project: FREE Real Time Project for Practice
    Close this tab and switch back to main window
    From top menu click on Selenium link that can be seen while hovering on Testing menu item
    Verify the wide red Join Now button is displayed near bottom of the page

