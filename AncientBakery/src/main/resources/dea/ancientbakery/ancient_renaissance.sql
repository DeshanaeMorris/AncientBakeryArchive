-- Recipe 7: Libum
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Libum (Sweet Cheesecake)',
           1,
           'Crush caseus well. Add farina and mix. Add one ovum and combine into dough. Shape into a loaf, place on folia laurea, and bake.',
           'Thoroughly crush 2 pounds of cheese in a mixing bowl. Once smooth, add 1 pound of flour and mix well with the cheese. Add 1 beaten egg and combine into a cohesive dough. Shape into a round loaf, place on a bed of bay leaves, and bake slowly under a hot clay pot.',
           'Bake slowly under a clay pot on the hearth (Modern oven: 190°C / 375°F for 35-40 mins)',
           1,
           'Cato the Elder, De Agri Cultura (Ch. 75)',
           'a025a-a1ebbb_45e39b5a8f834ca08285aa8c894fc898mv2.png'
       );
-- New ingredients for Recipe 7
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('bay leaves', 'spice');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (7, 6, 2, 'lb');     -- cheese
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (7, 1, 1, 'lb');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (7, 7, 1, 'whole');  -- eggs
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (7, 29, 4, 'whole'); -- bay leaves

-- Recipe 8: Mustacei
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Mustacei (Wine Cakes)',
           1,
           'Mix farina with grape must (mustum). Add anesum, cuminum, adeps, and grated caseus with folia laurea. Bake.',
           'Warm the grape juice and dissolve the yeast. In a large bowl, combine flour, ground cumin, and ground aniseed. Work in the grated cheese and lard until it resembles breadcrumbs. Pour in the liquid and knead for 5 minutes into a supple dough. Roll out to 1cm thickness, cut into rounds, place each cake on top of a bay leaf, and bake.',
           'Bake at 180°C (350°F) for 30-40 minutes',
           12,
           'Cato the Elder, De Agri Cultura (Ch. 121)',
           'must-cakes-2.jpg'
       );
-- New ingredients for Recipe 8
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('grape juice', 'other');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('aniseed', 'spice');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('cumin', 'spice');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (8, 1, 400, 'g');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (8, 30, 200, 'ml');   -- grape juice
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (8, 6, 60, 'g');      -- cheese
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (8, 31, 2, 'tsp');    -- aniseed
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (8, 32, 2, 'tsp');    -- cumin
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (8, 11, 60, 'g');     -- lard
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (8, 29, 12, 'whole'); -- bay leaves

-- Recipe 9: Bucellatum
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Bucellatum (Roman Biscuit)',
           1,
           'A bucellatum made of farina, sal, and aqua. Baked twice at low temperature so no moisture remains inside.',
           'Mix the flour, salt, and olive oil together in a bowl. Gradually add water while beating the mixture into a dense, whipped dough. Roll the dough flat and cut out clean round biscuits. Poke small holes across the surface of each round to allow trapped air and internal moisture to completely escape during baking. Bake, turning occasionally, until bone-dry and crisp.',
           'Bake at 120°C (250°F) for 2.5 hours, then allow to cool completely inside the oven.',
           6,
           'Codex Theodosianus (VII.4.6)',
           'hardtackplate-rotated.jpg'
       );
-- New ingredients for Recipe 9
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('water', 'other');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('olive oil', 'fat');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (9, 1, 350, 'g');    -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (9, 12, 1, 'tsp');   -- salt
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (9, 33, 75, 'ml');   -- water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (9, 34, 1, 'tbsp');  -- olive oil

-- Recipe 10: Ova Spongia
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Ova Spongia ex Lactate (Crepes w/ Milk)',
           1,
           'Mix ova, lac, and oleum into a batter. Cook in a sartagine with oleum. Top with mel and piper.',
           'Whisk the eggs, milk, and a small splash of olive oil together thoroughly until a smooth, thin pancake batter forms. Heat a small amount of olive oil in a frying pan. Pour a thin layer of batter into the pan to cook. Once flipped and cooked through, transfer to a plate. Drizzle generously with warm honey and finish with a light dusting of freshly cracked black pepper.',
           'Cook over a medium-low flame in a shallow frying pan until golden on both sides.',
           4,
           'Apicius, De Re Coquinaria (Book VII.XI)',
           'OSEL-whole-and-cut.jpg'
       );
-- New ingredients for Recipe 10
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('honey', 'sweetener');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('black pepper', 'spice');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (10, 7, 8, 'whole');  -- eggs
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (10, 16, 600, 'ml');  -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (10, 34, 100, 'ml');  -- olive oil
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (10, 35, 3, 'tbsp');  -- honey
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (10, 36, 1, 'pinch'); -- black pepper

-- Recipe 11: Panis Sourdough
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Panis Sourdough (Roman Sourdough Bread)',
           1,
           'Mix farina with water and grape must (mustum) to make a wild starter. Once active, add remaining farina, aqua, and sal. Knead, let rise, shape, and bake.',
           'In a small bowl, mix a portion of flour with warm water and fresh grape must, allowing it to ferment in a warm place for 2 to 3 days until bubbly and active to create your wild starter. Once active, combine this starter with the remaining flour, water, and salt in a large mixing bowl. Knead vigorously for 10-15 minutes until a smooth, elastic dough forms. Cover and let rise for 4-6 hours until doubled in size. Shape into a round, scored loaf and bake on a preheated stone or inside a covered earthenware pot.',
           'Bake at 220°C (425°F) for 35-40 minutes, preferably with steam or inside a closed Dutch oven.',
           2,
           'Pliny the Elder, Naturalis Historia (Book XVIII)',
           '20210916_144727-Copy.jpg'
       );
-- No new ingredients for Recipe 11
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (11, 1, 500, 'g');   -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (11, 33, 350, 'ml'); -- water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (11, 30, 50, 'ml');  -- grape juice
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (11, 12, 10, 'g');   -- salt
