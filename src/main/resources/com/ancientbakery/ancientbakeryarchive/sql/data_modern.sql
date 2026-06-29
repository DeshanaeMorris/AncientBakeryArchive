-- Recipe 30: Focaccia
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Focaccia',
           6,
           'No Original Text',
           'Whisk together flour, salt, and instant yeast in a large bowl. Add lukewarm water and mix with a spatula until a sticky dough forms. Coat with olive oil, cover, and refrigerate for at least 12 hours or up to 3 days. Transfer dough to a buttered and oiled 9x13 inch pan and let rest 2-4 hours at room temperature. Drizzle with olive oil, press deep dimples all over with fingers, sprinkle with flaky sea salt and rosemary if using. Bake until golden all around.',
           'Bake at 220°C (425°F) for 25-30 minutes',
           12,
           'Alexandra Stafford, Alexandra''s Kitchen (alexandracooks.com, 2018)',
           NULL
       );
-- New ingredients for Recipe 30
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('instant yeast', 'rising agent');   -- 70
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('flaky sea salt', 'spice');         -- 71
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('rosemary', 'spice');               -- 72
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (30, 9, 512, 'g');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (30, 20, 12, 'g');     -- salt
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (30, 70, 8, 'g');      -- instant yeast
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (30, 5, 455, 'g');     -- water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (30, 6, 4, 'tbsp');    -- olive oil
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (30, 71, 1, 'tsp');    -- flaky sea salt
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (30, 72, 2, 'tsp');    -- rosemary

-- Recipe 31: Sourdough Bread
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Sourdough Bread',
           6,
           'No Original Text',
           'Feed your sourdough starter 4-12 hours before baking until active and bubbly. Mix starter with warm water, then add flour and salt and mix until combined. Perform stretch and fold every 30 minutes for 2 hours. Shape into a round loaf and place in a floured banneton or bowl. Cold proof in the fridge overnight. Preheat oven with a Dutch oven inside. Score the top of the loaf, then bake covered to create steam, then uncovered to brown the crust.',
           'Bake covered at 230°C (450°F) for 20 minutes, then uncovered for 20-25 minutes',
           2,
           'Lisa Bass, Farmhouse on Boone (farmhouseonboone.com, 2023)',
           NULL
       );
-- New ingredients for Recipe 31
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('sourdough starter', 'rising agent'); -- 73
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (31, 9, 450, 'g');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (31, 5, 350, 'ml');    -- water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (31, 20, 9, 'g');      -- salt
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (31, 73, 100, 'g');    -- sourdough starter

-- Recipe 32: Blueberry Muffins
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Blueberry Muffins',
           6,
           'No Original Text',
           'Whisk together flour, baking powder, baking soda, and salt in a bowl. In a separate bowl, whisk melted butter, sugar, eggs, sour cream, milk, and vanilla. Fold wet ingredients into dry until just combined — do not overmix. Gently fold in blueberries. Divide batter into a lined muffin tin. Optionally sprinkle tops with coarse sugar. Bake until a toothpick inserted in the center comes out clean.',
           'Bake at 190°C (375°F) for 20-25 minutes',
           12,
           'Meggan Hill, Culinary Hill (culinaryhill.com, 2022)',
           NULL
       );
-- New ingredients for Recipe 32
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('baking soda', 'rising agent');     -- 74
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('sour cream', 'dairy');             -- 75
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('vanilla extract', 'other');        -- 76
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 9, 250, 'g');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 46, 2, 'tsp');    -- baking powder
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 74, 1, 'tsp');    -- baking soda
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 20, 1, 'tsp');    -- salt
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 10, 115, 'g');    -- butter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 21, 200, 'g');    -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 15, 2, 'whole');  -- eggs
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 75, 120, 'ml');   -- sour cream
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 24, 60, 'ml');    -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 76, 1, 'tsp');    -- vanilla extract
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (32, 68, 300, 'g');    -- blueberries

-- Recipe 33: Dutch Apple Pie
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Dutch Apple Pie',
           6,
           'No Original Text',
           'Make pie crust and press into a 9-inch pie dish, crimp edges and refrigerate. Peel and slice apples and toss with sugar, brown sugar, flour, cinnamon, nutmeg, and lemon juice. Pour filling into chilled crust. Make crumble topping by mixing flour, brown sugar, and cold butter with fingers until clumpy. Spread crumble evenly over the apple filling. Bake until the topping is golden and the filling is bubbling.',
           'Bake at 200°C (400°F) for 55-65 minutes',
           8,
           'Michelle Lettrich, Brown Eyed Baker (browneyedbaker.com, 2023)',
           NULL
       );
-- New ingredients for Recipe 33
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('lemon juice', 'other');            -- 77
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (33, 58, 1, 'whole');  -- pie crust
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (33, 13, 6, 'whole');  -- apples
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (33, 21, 100, 'g');    -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (33, 63, 100, 'g');    -- brown sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (33, 9, 30, 'g');      -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (33, 18, 2, 'tsp');    -- cinnamon
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (33, 27, 1, 'tsp');    -- nutmeg
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (33, 77, 1, 'tbsp');   -- lemon juice
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (33, 10, 115, 'g');    -- butter

-- Recipe 34: Easy Homemade Biscuits
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Homemade Biscuits',
           6,
           'No Original Text',
           'Whisk together flour, baking powder, sugar, and salt. Cut cold butter into the flour mixture using a pastry cutter or your fingers until it resembles coarse crumbs with pea-sized pieces of butter throughout. Add cold buttermilk and stir until just combined — do not overmix. Turn out onto a floured surface, gently pat to 3/4 inch thickness, and cut into rounds. Place on a baking sheet and bake until golden brown on top.',
           'Bake at 220°C (425°F) for 10-13 minutes',
           6,
           'Sam Merritt, Sugar Spun Run (sugarspunrun.com, 2018)',
           NULL
       );
-- New ingredients for Recipe 34
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('buttermilk', 'dairy');             -- 78
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (34, 9, 240, 'g');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (34, 46, 1, 'tbsp');   -- baking powder
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (34, 21, 1, 'tsp');    -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (34, 20, 1, 'tsp');    -- salt
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (34, 10, 115, 'g');    -- butter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (34, 78, 180, 'ml');   -- buttermilk

-- Recipe 35: Homemade Croissants
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Croissants',
           6,
           'No Original Text',
           'Make the dough by combining flour, sugar, salt, yeast, milk, and butter. Knead until smooth then refrigerate overnight. Pound cold butter into a flat square for the butter block. Roll dough into a large rectangle and enclose the butter block inside. Perform a series of folds and turns, chilling between each, to create the laminated layers. Cut into triangles, roll from base to tip, and curve into crescent shapes. Let proof until puffy, brush with egg wash, and bake until deep golden brown.',
           'Bake at 200°C (400°F) for 18-20 minutes',
           12,
           'Sally McKenney, Sally''s Baking Addiction (sallysbakingaddiction.com, 2018)',
           NULL
       );
-- No new ingredients for Recipe 35
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (35, 9, 480, 'g');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (35, 21, 50, 'g');     -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (35, 20, 10, 'g');     -- salt
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (35, 70, 7, 'g');      -- instant yeast
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (35, 24, 300, 'ml');   -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (35, 10, 280, 'g');    -- butter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (35, 15, 1, 'whole');  -- eggs