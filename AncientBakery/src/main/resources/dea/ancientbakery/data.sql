--Populate Eras table
INSERT INTO Eras (id, name, time_period, History_text) VALUES
    (1, 'Ancient', '3000 BC - 476 AD', 'Baking foundations born in early civilizations, relying heavily on flatbreads, wood-fired hearths, clay domes, and wild leavening agents like sourdough. Honey, olive oil, and ancient grains like spelt and emmer ruled the kitchens of Greece and Rome.'),
    (2, 'Medieval', '476 AD - 1453 AD', 'The rise of commercial guild bakers, heavy rye breads, and heavily spiced holiday cakes. Baking moved toward communal village ovens, introducing dense travel breads, early meat pastries, and ale-yeast skimming techniques.'),
    (3, 'Renaissance', '1453 AD - 1600 AD', 'A culinary explosion fueled by global exploration and the sugar trade. Baking shifted into an art form with enriched brioche-style doughs, puff pastries, delicate fruit tarts, and elaborate sugar sculptures tailored for noble courts.'),
    (4, 'Industrial', '1600 AD - 1900 AD', 'The birth of automated milling, commercial manufacturing, and reliable chemical leavening agents like pearlash and early baking powders. Cast-iron woodstoves replaced brick hearths, making precise temperature control possible for households.'),
    (5, '20th Century', '1900 AD - 2000 AD', 'The era of convenience, pre-packaged baking mixes, and global standardization. High-speed commercial mixers, electric ovens, sliced sandwich bread, and optimized instant yeast strains transformed baking from a grueling daily chore into an efficient science.'),
    (6, 'Modern', '2000 AD - Present', 'A diverse landscape combining rapid technological innovation with historical revival. Contemporary baking features advanced tools like digital scales, sourdough preservation movements, gluten-free grain alternatives, and global recipe sharing via digital archives.');

INSERT INTO Recipes (id, name, era_id, original_text, modernized_text, temperature_description, Sources, base_portion) VALUES (
   1,
   'Libum (Sweet Cheesecake)',
   1,
   'Libum hoc modo facito. Briga duas libras casei bene disterat in mortario. Vbi bene distererit, farinae siligineae libram aut, si voles tenerius esse, selibram alicae eodem adiciat conmisceatque cum caseo bene; ovum unum addito et una conmisceto bene. Inde panem facito, folia Laurea subditonis, in foco caldo sub testu coquito leniter.',
   'Thoroughly crush 2 pounds of cheese in a mixing bowl or mortar. Once smooth, add 1 pound of wheat flour (or a half-pound of fine semolina for a lighter texture) and mix it well with the cheese. Add 1 beaten egg and combine everything into a cohesive dough. Shape the mixture into a round loaf, place it on a bed of bay leaves, and bake slowly under a hot clay pot.',
   'Bake slowly under a clay pot on the hearth (Modern oven: 190°C / 375°F for 35-40 mins)',
   'Cato the Elder, De Agri Cultura (Ch. 75)',
   1
     );
INSERT INTO Ingredients (id, name, category) VALUES
       (101, 'Caseus', 'Dairy'),
       (102, 'Farina', 'Grains'),
       (103, 'Ovum', 'Dairy'),
       (104, 'Folia Laurea', 'Spices/Herbs');
INSERT INTO Recipe_Ingredients (id, Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES
        (1, 1, 101, 2.0, 'pounds'),
        (2, 1, 102, 1.0, 'pound'),
        (3, 1, 103, 1.0, 'whole'),
        (4, 1, 104, 4.0, 'leaves');
INSERT INTO Glossary (id, Term, Definition, Modern_Substitute) VALUES
       (501, 'Caseus', 'Ancient Roman word for fresh cheese, usually structural and salted.', 'Ricotta or Feta'),
       (502, 'Farina', 'The general Latin term for flour or milled grain.', 'All-Purpose or Spelt Flour'),
       (503, 'Mortarium', 'A heavy Roman earthenware or marble mixing bowl used with a pestle.', 'Mixing bowl or Mortar and Pestle'),
       (504, 'Testum', 'An ancient earthenware baking dome placed over hot embers.', 'Dutch Oven or upside-down clay pot');

INSERT INTO Recipes (id, name, era_id, original_text, modernized_text, temperature_description, Sources, base_portion)
VALUES (
     2,
     'Mustacei (Wine Cakes)',
     1,
     'Mustaceos sic facito. Farinae siligineae modium unum musto conspargito. Anesum, cuminum, adipis P. II, casei libram, et de virga lauri deradito, eodem addito, et ubi definxeris, lauri folia subtus addito, cum coques.',
     'Warm the Mustum to body temperature and dissolve the yeast. In a large bowl, combine Farina, ground Cuminum, and ground Anesum. Work in the grated Caseus and lard until it resembles breadcrumbs. Pour in the liquid mixture and knead for 5 minutes into a supple dough. Roll out to 1cm thickness, cut into rounds using a pastry cutter, place each cake on top of a Folia Laurea, and bake.',
     'Bake at 180°C (350°F) for 30-40 minutes',
     'Cato the Elder, De Agri Cultura (Ch. 121)',
     12
       );
INSERT INTO Ingredients (id, name, category) VALUES
      (202, 'Mustum', 'Liquids'),
      (204, 'Anesum', 'Spices/Herbs'),
      (205, 'Cuminum', 'Spices/Herbs'),
      (206, 'Adeps', 'Animal Fats');

INSERT INTO Recipe_Ingredients (id, Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES
      (5, 2, 102, 400.0, 'grams'),    -- 400g Farina
      (6, 2, 202, 200.0, 'milliliters'), -- 200ml Mustum (grape juice)
      (7, 2, 101, 60.0, 'grams'),     -- 60g Caseus (Pecorino/Cheddar)
      (8, 2, 204, 2.0, 'teaspoons'),   -- 2 tsp Anesum (aniseed)
      (9, 2, 205, 2.0, 'teaspoons'),   -- 2 tsp Cuminum (cumin)
      (10, 2, 206, 60.0, 'grams'),    -- 60g Adeps (lard/shortening)
      (11, 2, 104, 12.0, 'leaves');   -- 12 Folia Laurea (bay leaves)
INSERT INTO Glossary (id, Term, Definition, Modern_Substitute) VALUES
       (601, 'Mustum', 'Fresh, unfermented grape juice pressed during the wine-making harvest.', 'Red or White Grape Juice'),
       (602, 'Anesum', 'Aniseed, an aromatic Mediterranean spice heavily used by Romans to aid digestion.', 'Ground Aniseed'),
       (603, 'Cuminum', 'Cumin, a valuable spice used in both savory baking and medical treatments.', 'Ground Cumin'),
       (604, 'Adeps', 'Animal fat or lard, the traditional baking shortening used before commercial vegetable oils.', 'Pastry Lard or Butter'),
       (605, 'Folia Laurea', 'Bay laurel leaves, traditionally placed underneath dough to give herbal flavor and prevent sticking to hot stones.', 'Bay Leaves');

INSERT INTO Recipes (id, name, era_id, original_text, modernized_text, temperature_description, sources, base_portion)
VALUES (
      3,
      'Bucellatum (Roman Biscuit)',
      1,
      'Bucellatum ac panem, vinum quoque atque acetum, sed et laridum, carnem verbecinam. Bucellatum dry biscuit made of flour, salt and water, baked twice at low temperatures for a long time to ensure that no moisture got inside.',
      'Mix the Farina, salt, and olive oil together in a bowl. Gradually add water while beating the mixture into a dense, whipped dough. Roll the dough flat and cut out clean round biscuits. Poke small holes across the surface of each round to allow trapped air and internal moisture to completely escape during baking. Bake, turning occasionally, until bone-dry and crisp.',
      'Bake at 120°C (250°F) for 2.5 hours, then allow to cool completely inside the oven for several hours.',
      'Codex Theodosianus (VII.4.6)',
      6
     );
INSERT INTO Ingredients (id, name, category) VALUES
        (301, 'Sal', 'Spices/Herbs'),
        (302, 'Aqua', 'Liquids'),
        (303, 'Oleum', 'Fats/Oils'),
        (304, 'Posca', 'Liquids');

INSERT INTO Recipe_Ingredients (id, Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES
      (12, 3, 102, 350.0, 'grams'),       -- Links to existing Farina (102)
      (13, 3, 301, 1.0, 'teaspoon'),
      (14, 3, 302, 75.0, 'milliliters'),
      (15, 3, 303, 1.0, 'tablespoon'),
      (16, 3, 304, 1.0, 'cup');

INSERT INTO Glossary (id, Term, Definition, Modern_Substitute) VALUES
       (701, 'Bucellatum', 'A hard, twice-baked Roman military biscuit ration designed to last for months without spoiling.', 'Hardtack or unsalted soup crackers'),
       (702, 'Posca', 'A popular daily Greco-Roman beverage made by mixing sour wine or wine vinegar with fresh water and aromatic herbs.', 'Water mixed with a splash of red wine vinegar'),
       (703, 'Oleum', 'Pure olive oil, a staple of Mediterranean cooking, body care, and lighting fuel.', 'Extra Virgin Olive Oil');

INSERT INTO Recipes (id, name, era_id, original_text, modernized_text, temperature_description, sources, base_portion)
VALUES (
           4,
           'Ova Spongia ex Lactate (Crepes w/ Milk)',
           1,
           'Ova spongia ex lactate: ova, lac, oleum conmisceto ut batutum facias. In sartagine oleum mittito, ibi coques. Superficiem perfundes melle, ac piper adsparges.',
           'Whisk the eggs, milk, and a small splash of olive oil together thoroughly until a smooth, thin pancake batter forms. Heat a small amount of olive oil in a frying pan. Pour a thin layer of batter into the pan to cook. Once flipped and cooked through, transfer to a plate. Drizzle generously with warm honey and finish with a light dusting of freshly cracked black pepper.',
           'Cook over a medium-low flame in a shallow frying pan until golden on both sides.',
           'Apicius, De Re Coquinaria (Book VII.XI)',
           4
       );

INSERT INTO Ingredients (id, name, category) VALUES
      (401, 'Lac', 'Dairy'),
      (402, 'Piper', 'Spices/Herbs'),
      (403, 'Mel', 'Sweeteners');

INSERT INTO Recipe_Ingredients (id, Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES
       (17, 4, 103, 8.0, 'whole'),         -- Links to existing Ovum (103)
       (18, 4, 401, 600.0, 'milliliters'), -- Links to new Lac (401)
       (19, 4, 303, 100.0, 'milliliters'), -- Links to existing Oleum (303)
       (20, 4, 403, 3.0, 'tablespoons'),   -- Links to new Mel (403)
       (21, 4, 402, 1.0, 'pinch');         -- Links to new Piper (402)

INSERT INTO Glossary (id, Term, Definition, Modern_Substitute) VALUES
       (801, 'Ova Spongia', 'Literally translated as "spongy eggs," this is the ancient Roman ancestor of modern crepes.', 'Thin dessert crepes or pancakes'),
       (802, 'Lac', 'Fresh milk, sourced from goats or sheep in the ancient Roman world rather than cows.', 'Whole cow milk or goat milk'),
       (803, 'Piper', 'Black pepper, an luxury spice imported into Rome from India, used in both savory food and sweet desserts.', 'Black Pepper or Long Pepper');

INSERT INTO Recipes (id, name, era_id, original_text, modernized_text, temperature_description, sources, base_portion)
VALUES (
           5,
           'Panis Sourdough (Roman Sourdough Bread)',
           1,
           'Plinius Major, Naturalis Historia: Galliae et Hispaniae frumento in cervisiam坚 conspisso spuma ita concreta pro fermento utuntur, qua de causa levior illis quam ceteris panis est. Roman sourdough method utilizing wild yeast starter derived from fermenting grape must and wheat bran.',
           'In a small bowl, mix a portion of Farina with warm water and fresh Mustum, allowing it to ferment in a warm place for 2 to 3 days until bubbly and active to create your wild starter. Once active, combine this starter with the remaining Farina, Aqua, and Sal in a large mixing bowl. Knead vigorously for 10-15 minutes until a smooth, elastic dough forms. Cover and let rise for 4-6 hours until doubled in size. Shape into a round, scored loaf and bake on a preheated stone or inside a covered earthenware pot.',
           'Bake at 220°C (425°F) for 35-40 minutes, preferably with steam or inside a closed Dutch oven.',
           'Pliny the Elder, Naturalis Historia (Book XVIII)',
           2
       );

INSERT INTO Recipe_Ingredients (id, Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES
    (22, 5, 102, 500.0, 'grams'),
    (23, 5, 302, 350.0, 'milliliters'),
    (24, 5, 202, 50.0, 'milliliters'),
    (25, 5, 301, 10.0, 'grams');

INSERT INTO Glossary (id, Term, Definition, Modern_Substitute) VALUES
    (901, 'Panis Sourdough', 'The classic Roman sourdough method relying on wild yeasts cultivated from grain fields and fruit sugars.', 'Artisan Sourdough Bread'),
    (902, 'Fermentum', 'The ancient Roman term for a leavening agent or wild yeast starter culture, often preserved from a previous batch of dough.', 'Sourdough Starter or Wild Levain');