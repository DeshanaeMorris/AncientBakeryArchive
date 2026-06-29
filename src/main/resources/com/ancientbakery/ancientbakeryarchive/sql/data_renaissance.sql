--Renaissance Recipes

-- Insert Recipe 1
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Apple and Cheese Pie',
           3,
           'Take apples, peel them and grate them, then fry them in fat. Then add in as much grated cheese as apples, some ground cloves, a little ginger and cinnamon, two eggs. Mix it all together. Then make a dough as for a flat cake, and add a little fat into it so that it does not rise, and from over and under, a little heat, let it bake.',
           'Grate peeled apples and fry in butter. Mix with equal amount of grated cheese, cloves, ginger, cinnamon, and eggs. Pour into a shortcrust pastry and bake at 350°F for 45-50 minutes until set.',
           'From over and under, a little heat',
           8,
           'https://www.tastinghistory.com/recipes/applecheesepie',
           'apple_cheese_pie.jpg'
       );
--Ingredients
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('flour', 'grain');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('butter', 'fat');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('egg yolks', 'protein');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('saffron', 'spice');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('apples', 'fruit');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('cheese', 'dairy');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('eggs', 'protein');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('cloves', 'spice');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('ginger', 'spice');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('cinnamon', 'spice');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('lard', 'fat');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('salt', 'spice');

-- For converter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 1, 240, 'g');   -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 2, 85, 'g');    -- butter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 3, 2, 'whole'); -- egg yolks
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 4, 1, 'tbsp');  -- saffron
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 5, 5, 'whole'); -- apples
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 6, 340, 'g');   -- cheese
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 7, 2, 'whole'); -- eggs
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 8, 0.125, 'tsp'); -- cloves
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 9, 0.75, 'tsp'); -- ginger
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 10, 1, 'tsp');  -- cinnamon
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 11, 43, 'g');   -- lard
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (1, 12, 0.25, 'tsp'); -- salt

-- Recipe 2

-- Insert Recipe 2
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Prince Biskets',
           3,
           'Take one pounde of verie fine flower, and one pounde of fine sugar, and eight eggs, and two spoonfuls of Rosewater, and one ounce of carroway seeds, and beat it all to batter one whole hour, for the more you beat it, the better your bread is, then bake it in coffins of white plate, beeing basted with a little butter before you put in your batter, and so keepe it.',
           'Mix eggs, flour, sugar, rose water and caraway seeds. Beat for 1 hour by hand or 12-15 minutes in a stand mixer. Spoon into greased molds and bake at 350°F for 15 minutes until a toothpick comes out clean.',
           'Bake in coffins of white plate',
           12,
           'https://www.tastinghistory.com/recipes/princebiskets',
           'prince_biskets.jpg'
       );

-- Insert Ingredients (only adding ingredients not previously added)
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('sugar', 'sweetener');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('rose water', 'other');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('caraway seeds', 'spice');


-- Recipe 2 Ingredientsim
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (2, 7, 7, 'whole');   -- eggs
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (2, 1, 360, 'g');    -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (2, 25, 360, 'g');   -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (2, 26, 2, 'tsp');   -- rose water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (2, 27, 20, 'g');    -- caraway seeds

-- Recipe 3

-- Insert Recipe 3
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Dutch Pudding',
           3,
           'Take half a pound of butter melt it in half a Pint of milk 5 eggs leaving out 2 whites 3 spoonfulls of yeast a small tea cup of brandy a quarter of a pound of sugar one nutmeg grated into it, mix all these well together with a pound of flour let it stand by the fire to rise an hour then put in 3 quarters of a pound of currants butterr the Pan well an hour and half will bake it.',
           'Melt butter in milk over gentle heat. Beat 3 whole eggs and 2 yolks, add yeast, brandy, sugar, and grated nutmeg. Stir in flour until smooth. Let rise covered in a warm place for 1 hour. Fold in currants, pour into a buttered pan and bake at 340°F for 90 minutes until golden and set.',
           'Let it stand by the fire to rise, bake an hour and half',
           12,
           'https://lostkitchenscrolls.org/cookbook-of-elizabeth-langley-manuscript-bib222421-25-to-make-a-dutch-pudding',
           'dutch_pudding.jpg'
       );

-- New Ingredients only
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('milk', 'dairy');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('yeast', 'rising agent');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('brandy', 'other');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('nutmeg', 'spice');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('currants', 'fruit');

-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (3, 2, 8, 'oz');      -- butter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (3, 28, 9.5, 'fl oz'); -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (3, 7, 5, 'whole');   -- eggs
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (3, 29, 3, 'tbsp');   -- yeast
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (3, 30, 0.25, 'cup'); -- brandy
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (3, 25, 4, 'oz');     -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (3, 31, 1, 'whole');  -- nutmeg
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (3, 1, 16, 'oz');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (3, 32, 12, 'oz');    -- currants

-- Recipe 4
-- Insert Recipe 4
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Almond Florandine',
           3,
           'Take a pound of Almonds lay them in water and blanch them then beat them in a morter with a little rose water fine then put to them yolks of 6 eggs 2 whites a qvart of milk or Cream some rose water and Sack and nutmeg and beaten Cinnamon some sweet butterr and grated bread and Sugar beat it altogether and fill your florandine - with puffe past under and over it',
           'Blanch and grind almonds with rose water. Mix with 6 egg yolks, 2 whites, cream, rose water, sherry, nutmeg, cinnamon, melted butter, breadcrumbs and sugar. Pour into a puff pastry lined dish, cover with more pastry and bake at 350°F for 35-40 minutes until golden.',
           'Bake in a wood-fired oven or hearth',
           8,
           'https://lostkitchenscrolls.org/cookbook-of-mary-cruso-and-timothy-cruso-manuscri-bib193255-28-an-almond-florandine',
           'almond_florandine.jpg'
       );

-- New Ingredients only
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('almonds', 'other');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('cream', 'dairy');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('sherry', 'other');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('breadcrumbs', 'grain');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('puff pastry', 'other');

-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 21, 16, 'oz');    -- almonds
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 14, 2, 'tsp');   -- rose water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 3, 6, 'whole');  -- egg yolks
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 7, 2, 'whole');  -- eggs (whites)
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 16, 1, 'cup');   -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 22, 1, 'cup');   -- cream
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 23, 3, 'tbsp');  -- sherry
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 19, 0.5, 'tsp'); -- nutmeg
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 10, 0.5, 'tsp'); -- cinnamon
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 2, 2, 'tbsp');   -- butter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 24, 2, 'oz');    -- breadcrumbs
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 13, 0.5, 'cup'); -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (4, 25, 16, 'oz');   -- puff pastry

-- Recipe 5
-- Insert Recipe 5
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Rice Pancakes',
           3,
           'Eake a quarter of apound of rice boylo it very lentle when yo ran make it Smale. with a Back Side of a Spoon put as much milke to it as will make it pretty thin then with find flower make it of a thicker batter then put in 4 Eggs and a quarter of a pound of melted Butter Beats it well and lett it Stand 12 howers before yo fry them or yo may doe with fresh Butter and not make yo very thin throw Suger & y Juces of a Lemmon over them /',
           'Simmer rice gently until very soft, then mash smooth. Add enough milk to make a loose batter. Sift in flour until thick. Stir in 4 eggs and melted butter. Let batter rest covered for 12 hours. Fry in butter and serve sprinkled with sugar and fresh lemon juice.',
           'Fry in a heavy iron skillet over a fire',
           6,
           'https://lostkitchenscrolls.org/cookbook-of-constance-hall-manuscript-bib226684-28-to-make-rice-pancakes',
           'rice_pancakes.jpg'
       );

-- Ingredients
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('rice', 'grain');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('lemon', 'fruit');

-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (5, 27, 4, 'oz');    -- rice
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (5, 16, 1, 'cup');   -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (5, 1, 0.5, 'cup');  -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (5, 7, 4, 'whole');  -- eggs
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (5, 2, 4, 'oz');     -- butter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (5, 13, 1, 'tbsp'); -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (5, 28, 1, 'whole'); -- lemon

-- Recipe 6
-- Insert Recipe 6
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Mackerons',
           3,
           'take a pound of almonds blanch them in hot water and put them into cold then dry them in a cloth and beate them in a stone morter drope in now and then a littel ros water to keep them from oyling and when ye are beate indifferent fine then put in as lite ros water as posibel then put in the whites of 4 eges whisk up to a froth and stor them well together and lay them out on papers with out wafers and back them on shets of tin and when ye are risen and have a fine coler & wel enough draw them and when ye are a littel cold take them of ye papers remeber to searce on suger when ye goe into ye oven and it will make them looke as if ye were icst',
           'Blanch almonds, dry and grind in a food processor with a few drops of rose water. Whisk 4 egg whites to a froth and fold into almond mixture. Spoon onto parchment lined baking sheets, sift sugar over tops and bake at 300°F for 18-22 minutes until risen and golden.',
           'Back them on shets of tin in a hot wood-fired oven',
           18,
           'https://lostkitchenscrolls.org/medicinal-and-cookery-recipes-manuscript-bib230663-27-to-make-mackerons',
           'mackerons.jpg'
       );

-- No new ingredients needed

-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (6, 21, 16, 'oz');   -- almonds
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (6, 14, 2, 'tsp');  -- rose water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (6, 7, 4, 'whole'); -- eggs (whites)
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (6, 13, 1, 'tbsp'); -- sugar