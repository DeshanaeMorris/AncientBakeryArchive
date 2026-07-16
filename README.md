# AncientBakeryArchive
AncientBakeryArchive
An Ancient Bakery Archive that contains recipes from older time periods to modern day.

**Eras Covered**

**Ancient**
10,000 BC – 500 AD
**Medieval**
500 – 1400
**Renaissance**
1400 – 1800
**Industrial**
1800 – 1900
**20th Century**
1900 – 2000
**Modern**
21st Century

Each era screen has its own visual theme (color palette and font) while sharing a consistent overall layout.
Features

**Browsing & Search**
- Chronological Era Interface: Users select an era filter toggle on the navigation window. The system queries the database, joining the recipes table to the eras table, and displays a panel of recipes matching the selected era ID.
  
- Word Searching: Users type letters or keywords into the main search bar. The system searches the database for matches within the recipe name or historical description and displays a filtered list of matching recipes.
  
- Ingredient Search: Users type in a raw ingredient (e.g., "basil"). The backend searches the ingredients table for every recipe containing that exact ingredient and outputs all matching records.
  
**Pantry Matching**
- Interactive Historical Pantry Matcher: Users check off kitchen ingredients they have from a sidebar checklist. The system cross-references the selection against the recipe database to show which ancient recipes can be made with those items.
  
- Multi-Select Pantry Exception Handler: If a user's ingredient combination matches nothing in the database, the system catches the empty result and displays a "No Recipes Found" popup message. 

**Scaling & Conversion**
- Recipe Portion Scaler: Users enter a multiplier (e.g., 2 for a double batch, 0.5 for half) into the recipe size box. The app multiplies the original recipe weights by that number, and the recipe page instantly updates with the scaled ingredient amounts. 

- Unit Converter: A toggle button switches a recipe's measurements between metric and imperial, converting raw data (grams, etc.) into ounces or cups as needed.
  
- Thermal Converter: Interprets vague historical baking instructions (e.g., "bake under wood") and translates them into modern oven temperatures.
  
- Dynamic Split Screen Layout: When a user clicks into a recipe, the Java control class splits the display into two panes: one showing the raw historical text, the other the modernized version.
  
**Glossary**
- Dynamic Glossary Substitution Tooltip: Hovering over a confusing historical term triggers an interactive tooltip with a definition and a modern substitute. 

- Analytics Dashboard
Sweetener Graph: On the Data Analytics Dashboard, the system counts honey vs. other sugar usage per era and renders the trend as a line graph showing the historical rise or decline of different sweeteners.

- Era Ingredient Breakdown Pie Chart: Users select an era; a pie chart shows the proportional breakdown of ingredient categories (grain, dairy, spice, fat, sweetener, etc.) for that era's recipes.

- Yeast Timeline: Maps rising agents used across eras into a timeline graph tracking the shift from wild sourdough to instant yeast.
  
- Cooking Fat Ranking: Totals how often cooking fats (butter, oil, etc.) appear across recipes and ranks them on a leaderboard from most to least common.

- Archive Stats Summary KPIs: On the main dashboard load, the system counts totals (e.g., total recipes stored) and displays them as quick-read numeric widgets.
  
**Tech Stack**
Language: Java
UI Framework: JavaFX
UI Layout: FXML, designed with Scene Builder
Database: SQLite
Build Tool: Maven
IDE: IntelliJ IDEA

**Built With**
Developed in IntelliJ IDEA as a Maven project, using JavaFX (added as a Maven dependency) for the UI and Scene Builder for FXML layout design.

**Database Schema**
Eras — chronological era metadata
Recipes — recipe framework per era, including original historical text
Ingredients — raw ingredient data linked to recipes
Glossary — historical/foreign terms with definitions and modern substitutes, linked to recipe text

**Team**
Sam D.
Ludy J.
Maycol M.
Deshanae M.
Deannee M.

**License**
This is a student project not intended for distribution.

