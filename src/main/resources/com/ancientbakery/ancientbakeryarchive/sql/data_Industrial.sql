-- Recipe 18: Ante De Mantequilla
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Ante De Mantequilla (Butter Custard)',
           4,
           'Put a pound of azucar over the heat to make a clear almibar cooked to firm stage; into this almibar add some mantequilla, and once it has cooled, add four yemas de huevos, then return to the heat so it cooks, adding a portion of toasted and ground almendra. Layer slices of mamon, and if you wish to add requeson, place it on the last layer, with a pastilla de olor, and compose well so that it sets between two heats.',
           'Heat sugar with water in a saucepan to make a clear syrup cooked to the thread stage. Add butter, stirring until melted. Allow to cool slightly, then beat in four egg yolks. Return to gentle heat, stirring constantly until thickened. Remove from heat and stir in toasted ground almonds. Layer slices of sponge cake in a deep dish, spreading the almond-butter custard between each layer. Optionally add ricotta on the final layer with a pinch of cinnamon or clove. Bake gently in a bain-marie until set.',
           'Bake in a bain-marie at 160°C (320°F) for 30-40 minutes',
           10,
           'Alejandro Valdes, Novisimo arte de cocina (1831)',
           'butter_custard.jpg'
       );
-- New ingredients for Recipe 18
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('sponge cake', 'grain');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('ricotta cheese', 'dairy');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('almond paste', 'other');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (18, 21, 450, 'g');    -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (18, 5, 75, 'ml');     -- water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (18, 10, 100, 'g');    -- butter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (18, 11, 4, 'whole');  -- egg yolks
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (18, 29, 80, 'g');     -- almonds
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (18, 42, 1, 'whole');  -- sponge cake
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (18, 43, 100, 'g');    -- ricotta cheese
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (18, 18, 1, 'tsp');    -- cinnamon

-- Recipe 19: Rullad Jelly Cake
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Rullad Jelly Cake (Rolled Jelly Cake)',
           4,
           '4 vispade eggs, 1 cup socker, 2 tablespoons water, 1 cup mjöl, 2 teaspoons baking powder. Bake for 20 minutes in a hot oven. While still warm, spread some kind of jelly on the cake, which is then rolled up and wrapped with a handduk. Store in a cool place until ready to serve. Cut into skifvor with a sharp knife.',
           'Whisk 4 eggs with sugar until light and foamy. Add water and mix well. Fold in sifted flour and baking powder. Pour into a lined Swiss roll tin and bake until golden. While still warm, spread jelly evenly over the surface. Roll up firmly using a towel to guide, wrap and chill until set. Slice into rounds to serve.',
           'Bake at 200°C (400°F) for 20 minutes',
           10,
           'C. A. Vallentin, Praktisk illustrerad kok-bok for Svenskarne i Amerika (1889)',
           'rolled_jelly_cake.jpg'
       );
-- New ingredients for Recipe 19
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('baking powder', 'rising agent');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('jam or jelly', 'other');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (19, 7, 4, 'whole');   -- eggs
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (19, 21, 200, 'g');    -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (19, 5, 2, 'tbsp');    -- water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (19, 1, 150, 'g');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (19, 45, 2, 'tsp');    -- baking powder
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (19, 46, 150, 'g');    -- jam or jelly

-- Recipe 20: Smörbakelse
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Smörbakelse (Butter Pastry)',
           4,
           'Smördeg is rolled out to 1/4 inch thickness and shaped with a metal cutter into half-moons. A mixture is made of socker and mandelmassa, stirred with beaten ägghvita until smooth and spreadable, then thinly spread on the bakelse with a knife. Sprinkle with fine socker and place in the oven to bake. Arrange in a pyramid on a folded servett.',
           'Roll out puff pastry to 1/4 inch thick and cut into half-moon shapes. Mix equal parts sugar and almond paste with stiffly beaten egg white until smooth and spreadable. Thinly spread filling onto each pastry. Sprinkle with fine sugar and bake until golden and crisp. Arrange in a pyramid on a folded napkin to serve.',
           'Bake at 200°C (400°F) for 15-20 minutes',
           10,
           'C. A. Vallentin, Praktisk illustrerad kok-bok for Svenskarne i Amerika (1889)',
           'butter_pastry.jpg'
       );
-- No new ingredients for Recipe 20
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (20, 33, 250, 'g');    -- puff pastry
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (20, 44, 70, 'g');     -- almond paste
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (20, 21, 70, 'g');     -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (20, 40, 1, 'whole');  -- egg whites

-- Recipe 21: Zuckerkastanien
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Zuckerkastanien (Sugar Chestnuts)',
           4,
           'Blanch almonds and pound them finely; take 4 Loth Zucker in a pan, pour in a little water, let it boil until it spins threads, add the almonds, and stir until they dry. Then sprinkle the Kastanien-Model with Zucker, press the dough into them, and knock out the shaped chestnuts; next, put 4 Eidotter, a teaspoon of Wein, and Zucker in a bowl, stick the chestnuts on a fork, dip them well, and roast them over a coal fire.',
           'Blanch almonds and grind finely. Cook sugar with a little water until it reaches the thread stage. Stir in ground almonds over low heat until dry and clumping. Press mixture into chestnut-shaped molds dusted with sugar, then knock out. Whisk egg yolks with white wine and sugar for a glaze. Spear each almond chestnut on a fork, dip into the glaze, and roast under a broiler or with a kitchen torch until set.',
           'Broil or torch briefly to set the glaze',
           10,
           'Marianka, Der Marianka Mundkochin des Hans-Jorgel von Gumpoldskirchen (1850)',
           'sugar_chestnuts.jpg'
       );
-- New ingredients for Recipe 21
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('white wine', 'other');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (21, 29, 100, 'g');    -- almonds
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (21, 21, 65, 'g');     -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (21, 5, 2, 'tbsp');    -- water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (21, 11, 4, 'whole');  -- egg yolks
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (21, 47, 1, 'tsp');    -- white wine

-- Recipe 22: Citronový Sulc
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Citronový Sulc (Lemon Jelly)',
           4,
           'For the whole mass, take twelve citrôny; rub four citrôny lightly over sugar and scrape them into a container with a knife; thinly peel the rinds from the remaining citrôny and put the peels into a bowl. Make a clear sirup from 16 lots of sugar, pour it boiling over the peels, and let it cool. Gently squeeze the juice from all the citrôny, dip them in clean water and squeeze again; mix everything together and add three lots of vizový mechúr. Strain several times through a servyt until clear, pour into a form prepared in ice, and let it sulcovať.',
           'Rub four lemons over sugar to collect zest, scrape into a bowl. Thinly peel the remaining lemons and add peels to the bowl. Make a clear syrup from sugar and water, pour boiling over the peels and let cool. Squeeze juice from all lemons, dip squeezed lemons in water and press again. Combine juice with syrup and dissolve gelatin into the mixture. Strain several times through a cloth until clear. Pour into a mold set over ice and leave to set into jelly.',
           'No baking - chill until set',
           8,
           'Unknown Author, Prva kucharska kniha v slovenskej reci (1870)',
           'lemon_jelly.jpg'
       );
-- New ingredients for Recipe 22
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('gelatin', 'other');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (22, 35, 12, 'whole'); -- lemon
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (22, 21, 265, 'g');    -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (22, 5, 500, 'ml');    -- water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (22, 48, 50, 'g');     -- gelatin

-- Recipe 23: Blandad Kompott
INSERT OR REPLACE INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Blandad Kompott (Mixed Compote)',
           4,
           'Torkade äpplen, päron, plommon, and korsbär are rinsed well and placed 12 hours before use in a bowl with enough cold water to cover the fruit by 2 inches. The bowl is covered and the next day the fruit and water are poured into a kastrull along with some orange or lemon juice and allowed to simmer slowly for 1-2 hours. If the juice is too thin it is thickened with potatismjöl or stärkelse. Serve with mjölk or grädde and strösocker.',
           'Rinse dried apples, pears, plums, and cherries and soak in cold water for 12 hours with water covering fruit by 2 inches. The next day, pour fruit and water into a saucepan with orange or lemon juice and simmer slowly for 1-2 hours. If too thin, thicken with potato starch dissolved in cold water. Serve warm or chilled with milk or cream and a sprinkling of sugar.',
           'Simmer on stovetop over low heat for 1-2 hours',
           6,
           'C. A. Vallentin, Praktisk illustrerad kok-bok for Svenskarne i Amerika (1889)',
           'mixed_compote.jpg'
       );
-- New ingredients for Recipe 23
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('dried apples', 'fruit');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('dried pears', 'fruit');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('dried plums', 'fruit');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('dried cherries', 'fruit');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('orange juice', 'other');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('potato starch', 'other');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (23, 49, 100, 'g');    -- dried apples
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (23, 50, 100, 'g');    -- dried pears
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (23, 51, 100, 'g');    -- dried plums
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (23, 52, 100, 'g');    -- dried cherries
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (23, 35, 2, 'tbsp');   -- lemon
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (23, 53, 2, 'tbsp');   -- orange juice
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (23, 54, 2, 'tbsp');   -- potato starch
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (23, 21, 2, 'tbsp');   -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (23, 30, 100, 'ml');   -- cream