--Populate Eras table
INSERT INTO Eras (id, name, time_period, History_text) VALUES
    (1, 'Ancient', '3000 BC - 476 AD', 'Baking foundations born in early civilizations, relying heavily on flatbreads, wood-fired hearths, clay domes, and wild leavening agents like sourdough. Honey, olive oil, and ancient grains like spelt and emmer ruled the kitchens of Greece and Rome.'),
    (2, 'Medieval', '476 AD - 1453 AD', 'The rise of commercial guild bakers, heavy rye breads, and heavily spiced holiday cakes. Baking moved toward communal village ovens, introducing dense travel breads, early meat pastries, and ale-yeast skimming techniques.'),
    (3, 'Renaissance', '1453 AD - 1600 AD', 'A culinary explosion fueled by global exploration and the sugar trade. Baking shifted into an art form with enriched brioche-style doughs, puff pastries, delicate fruit tarts, and elaborate sugar sculptures tailored for noble courts.'),
    (4, 'Industrial', '1600 AD - 1900 AD', 'The birth of automated milling, commercial manufacturing, and reliable chemical leavening agents like pearlash and early baking powders. Cast-iron woodstoves replaced brick hearths, making precise temperature control possible for households.'),
    (5, '20th Century', '1900 AD - 2000 AD', 'The era of convenience, pre-packaged baking mixes, and global standardization. High-speed commercial mixers, electric ovens, sliced sandwich bread, and optimized instant yeast strains transformed baking from a grueling daily chore into an efficient science.'),
    (6, 'Modern', '2000 AD - Present', 'A diverse landscape combining rapid technological innovation with historical revival. Contemporary baking features advanced tools like digital scales, sourdough preservation movements, gluten-free grain alternatives, and global recipe sharing via digital archives.');

-- Updating Eras with historical text data
UPDATE Eras SET history_text = 'Baking in the Ancient Era saw the transition from simple flatbreads cooked on hot stones to the invention of clay ovens and the discovery of wild yeast by the Egyptians. In Ancient Greece and Rome, baking became a highly respected trade; ' ||
                               'Rome alone boasted hundreds of public bakeries (pistrina) overseen by powerful bakers'' guilds. The everyday population survived on panis quadratus—a dense, circular loaf scored into eight sections, packed with emmer or barley flour and coriander or fennel.' ||
                               'Sweet baking was simple but popular, consisting of honey-soaked cakes like the Roman libum (a dense cheese-and-flour cake baked on bay leaves) ' ||
                               'and flatbreads brushed with olive oil and wild herbs.' WHERE id = 1;

UPDATE Eras SET history_text = 'In the Middle Ages, baking was strictly regulated by the crown to prevent fraud, and what kind of bread you ate was the indicator of your social standing in the hierarchy. ' ||
                               'The nobility consumed manchet, a meticulously bolted (sifted) fine white wheat bread that was light and airy. ' ||
                               'The working peasant class ate maslin or tourte, a heavy, dark, coarse bread made from a field-mix of rye, barley, and chaff. Because ovens were expensive and a massive fire hazard, communal village ovens were standard, where peasants paid a fee to have the village baker bake their dough. ' ||
                               'Sweet pastries were functional rather than soft or delicate. ' WHERE id = 2;

UPDATE Eras SET history_text = 'The Renaissance transformed baking from a survival craft into an visual art form, influenced by the influx of sugar from the New World and refined fats like dairy butter. ' ||
                               'Guild systems split into specialized branches, separating bread bakers from pastry chefs (pâtissiers). Banquets for the wealthy featured puff pastries, and sweet egg-leavened treats. ' ||
                               'This era saw the birth of ancestral holiday bakes still made today, such as Italian panettone and French choux pastry, alongside spiced, decorative gingerbreads stamped from wooden molds.' WHERE id = 3;

UPDATE Eras SET history_text = 'The Industrial Revolution took baking from small shops to automated factories, aiming for maximum yield and longer shelf life to feed rapidly growing city populations. ' ||
                               'The invention of roller-milling in the 19th century allowed mills to easily strip away the wheat bran and germ, creating refined white flour that was once accessible only to the wealthy. ' ||
                               'Concurrently, the discovery of chemical leaveners, potassium carbonate (pearlash), followed by baking soda and commercial baking powder, meant bakers no longer had to wait hours for yeast to rise. ' ||
                               'This gave birth to the modern cake, quick breads, and mass-market biscuits. ' ||
                               'Mass production line bakeries sprouted up across urban centers, stamping out thousands of uniform sandwich loaves and biscuits every hour.' WHERE id = 4;

UPDATE Eras SET history_text = 'The 20th century optimized baking for speed, convenience, and chemical preservation. ' ||
                               'In 1961, the development of the Chorleywood Bread Process revolutionized commercial white bread: by using high-speed mechanical shearing and chemical oxidizers, factories bypassed traditional fermentation entirely, turning raw flour into a soft, sliced sandwich loaf in under two hours. ' ||
                               'The post-war era championed domestic convenience, filling grocery aisles with boxed cake mixes (such as Betty Crocker), pre-made refrigerated biscuit dough in pop-tubes, and highly processed, long-lasting snack cakes (such as Twinkies). ' ||
                               'For the home baker, the electric stand mixer replaced intense manual kneading, turning baking into a precise, measured science based on standardized global recipes.' WHERE id = 5;

UPDATE Eras SET history_text = 'Today, the world of baking is split into two opposite directions. ' ||
                               'Industrial factories rely on heavy machinery and chemical additives such as dough conditioners and emulsifiers to mass-produce cheap, long-lasting baked goods for supermarkets.' ||
                               'However, there is a massive cultural comeback for traditional baking. ' ||
                               'Ignited by social media and 2020 lockdowns, a wave of home and professional bakers have embraced the "Sourdough Revival," ditching commercial yeast for slow, wild-yeast fermentation. ' ||
                               'At the same time, platforms such as TikTok have turned modern baking into a highly visual and complex art form. ' ||
                               'Today, intricate laminated croissants, geometric pastries, and hyper-realistic cakes continuously go viral.' Where id = 6;