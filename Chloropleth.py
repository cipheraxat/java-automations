import geopandas as gpd
import pandas as pd
import matplotlib.pyplot as plt

# Read in the UK postal code boundary data
uk_postcodes = gpd.read_file('https://github.com/missinglinkai/uk-postcodes/raw/main/postcodes.geojson')

# Download the UK population data from the ONS website
url = 'https://www.ons.gov.uk/file?uri=/peoplepopulationandcommunity/populationandmigration/populationestimates/datasets/populationestimatesforukenglandandwalesscotlandandnorthernireland/mid2020april2021localauthoritydistrictcodes/ukmidyearestimates20202021ladcodes.xls'
pop_data = pd.read_excel(url, sheet_name='MYE2 - Persons', header=4)

# Select the relevant columns and rename them for clarity
pop_data = pop_data[['Code', 'Name', 'All ages']]
pop_data = pop_data.rename(columns={'Code': 'ladcd', 'Name': 'ladnm', 'All ages': 'population'})

# Merge the population data with the postal code data using the local authority district code (ladcd)
uk_pop = uk_postcodes.merge(pop_data, on='ladcd')

# Plot the chloropleth map
uk_pop.plot(column='population', cmap='OrRd', legend=True)
plt.title('UK Population by Postal Code')
plt.show()
