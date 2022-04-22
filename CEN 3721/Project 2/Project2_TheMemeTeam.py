import numpy as np
import pandas as pd
import streamlit as st
import requests
import plotly.express as px
from streamlit_folium import folium_static
import folium
from geopy.geocoders import Nominatim

Air_Quality_Key = "7f8cdca1-619b-4f12-ab60-fbec373d25a9"

st.set_page_config(
    page_title="CEN 3721 - Project 2 - The Meme Team",
    page_icon="random",
    menu_items={
        'About': '# API showoff. Developed by The Meme Team'
    }
)

st.title("Project 2 - The Meme Team")


def map_creator(lat, long):
    m = folium.Map(location=[lat, long], zoom_start=5)
    folium.Marker([lat, long], popup="Station", tooltip="Station").add_to(m)
    folium_static(m)


def color_crypto(val):
    color = 'green' if float(val) > 0.0 else 'red'
    return f'background-color: {color}'


api_choice = st.selectbox("Select a topic of interest :", options=("", "Covid", "Crypto",))

if api_choice == "Covid":
    covid_country = "https://covid19.mathdro.id/api/countries"
    covid_dict = requests.get(covid_country).json()
    covid_list = []

    for i in covid_dict["countries"]:
        covid_list.append(i["name"])

    covid_list.insert(0, "")

    covid_selected = st.selectbox("Select a country:", options=covid_list)
    if covid_selected:
        covid_case_url = "https://covid19.mathdro.id/api/countries/{}".format(covid_selected)
        covid_case_data = requests.get(covid_case_url).json()
        covid_case_confirmed = covid_case_data["confirmed"]["value"]
        covid_case_deaths = covid_case_data["deaths"]["value"]

        st.info(
            "Number of covid case confirmed in {0} is {1} and {2} deaths".format(covid_selected, covid_case_confirmed,
                                                                                 covid_case_deaths))

        if 'covid_tab' not in st.session_state:
            st.session_state.antiBugGraph = False
            st.session_state.covid_tab = [[covid_selected, covid_case_confirmed, covid_case_deaths]]
        else:
            if covid_selected not in st.session_state.covid_tab:
                st.session_state.antiBugGraph = True
                newrow = [covid_selected, covid_case_confirmed, covid_case_deaths]
                st.session_state.covid_tab = np.vstack([st.session_state.covid_tab, newrow])
            print(st.session_state.covid_tab)

        hide_table_row_index = """<style>tbody th {display:none}.blank {display:none}</style>"""
        st.markdown(hide_table_row_index, unsafe_allow_html=True)
        df = pd.DataFrame(st.session_state.covid_tab, columns=("Country", "Cases confirmed", "Deaths"))
        st.table(df)

        if st.session_state.antiBugGraph:
            if st.checkbox('Display bar chart of covid cases'):
                df['Cases confirmed'] = df['Cases confirmed'].astype(int)
                df['Deaths'] = df['Deaths'].astype(int)
                fig = px.bar(df, x="Country", y=["Cases confirmed", "Deaths"], barmode='group')
                st.plotly_chart(fig)
        else:
            st.write("Add one more country to get be abble to display the chart")

        try:
            geolocator = Nominatim(user_agent="MemeTeam")
            location = geolocator.geocode(covid_selected)
            print((location.latitude, location.longitude))
            map_creator(location.latitude, location.longitude)
        except AttributeError:
            st.error("Country wasn't found")

        with st.expander("More about"):
            st.write("Coronavirus disease (COVID-19) is an infectious disease caused by the SARS-CoV-2 virus.")
            st.write("Data provided by the api: https://covid19.mathdro.id/api.")


elif api_choice == "Crypto":
    crypto_id_url = "https://www.cryptingup.com/api/assetsoverview"
    crypto_dict = requests.get(crypto_id_url).json()
    crypto_id_list = []

    for i in crypto_dict["assets"]:
        crypto_id_list.append(i["asset_id"])

    crypto_id_list.insert(0, "")

    crypto_selected = st.selectbox("Select the crypto:", options=crypto_id_list)
    if crypto_selected:
        crypto_data_url = "https://www.cryptingup.com/api/assets/{}".format(crypto_selected)
        crypto_data = requests.get(crypto_data_url).json()
        crypto_price = crypto_data["asset"]["price"]
        crypto_change_1h = crypto_data["asset"]["change_1h"]
        crypto_change_24h = crypto_data["asset"]["change_24h"]
        crypto_change_7d = crypto_data["asset"]["change_7d"]
        crypto_desc = crypto_data["asset"]["description"]

        st.info(
            "{0} is currently worth {1} and evolved by {2}% during the last hour".format(crypto_selected, crypto_price,
                                                                                         crypto_change_1h))
        if crypto_desc != "":
            st.info("{0}".format(crypto_desc))
        else:
            st.warning("There is currently no description for this crypto")

        if 'crypto_tab' not in st.session_state:
            st.session_state.crypto_tab = [
                [crypto_selected, crypto_price, crypto_change_1h, crypto_change_24h, crypto_change_7d]]

            # print(type(crypto_change_1h))
        else:
            if crypto_selected not in st.session_state.crypto_tab:
                newrow = [crypto_selected, crypto_price, crypto_change_1h, crypto_data["asset"]["change_24h"],
                          crypto_data["asset"]["change_7d"]]
                st.session_state.crypto_tab = np.vstack([st.session_state.crypto_tab, newrow])
            # print(st.session_state.crypto_tab)

        hide_table_row_index = """<style>tbody th {display:none}.blank {display:none}</style>"""
        st.markdown(hide_table_row_index, unsafe_allow_html=True)
        df = pd.DataFrame(st.session_state.crypto_tab,
                          columns=("Crypto", "Price", "1H change", "24H change", "7D change"))
        st.table(df.style.applymap(color_crypto, subset=['1H change', '24H change', '7D change']))

        d = {"test": ["7d", "24h", "1h", "now"], crypto_selected: [crypto_price - crypto_price * crypto_change_7d / 100,
                                                                   crypto_price - crypto_price * crypto_change_24h / 100,
                                                                   crypto_price - crypto_price * crypto_change_1h / 100,
                                                                   crypto_price]}
        chart_data = pd.DataFrame(
            {'date': ["7d", "24h", "1h", "0h"],
             crypto_selected: [crypto_price - crypto_price * crypto_change_7d / 100,
                               crypto_price - crypto_price * crypto_change_24h / 100,
                               crypto_price - crypto_price * crypto_change_1h / 100,
                               crypto_price]
             })
        chart_data = chart_data.rename(columns={'date': 'index'}).set_index('index')
        st.line_chart(chart_data)

        ammount = st.number_input("USD to {0} Conversion".format(crypto_selected), 0, None, 10000, 10, )
        genre = st.radio(
            "Get currency change:",
            ('Euro', 'Dollar', 'Pound'))
        st.write('<style>div.row-widget.stRadio > div{flex-direction:row;}</style>', unsafe_allow_html=True)

        if genre == 'Euro':
            st.write(f"€{ammount} Euro = {ammount / crypto_price * 0.92} {crypto_selected}")
        elif genre == 'Dollar':
            st.write(f"${ammount} USD = {ammount / crypto_price} {crypto_selected}")
        elif genre == 'Pound':
            st.write(f"£{ammount} Pound = {ammount / crypto_price * 0.77} {crypto_selected}")


else:
    result = st.button("Click here to know more about the project")
    if result:
        st.success("CEN3721 - Project 2 - The Meme Team")
        st.info("If the page refresh when choosing the crypto try again... It sometimes happen from the api"
                "\nAPI used:"
                "\n- Covid:  https://covid19.mathdro.id/api"
                "\n- Crypto: https://www.cryptingup.com/apidoc/"

                "\nCovid page:"
                "\n- st.selectbox: choose country from the select box"
                "\n- st.info: display data for the selected country"
                "\n- Table: table with the data of the selected country. Each time a country is selected add it to the table"
                "\n- Map: show choosen country"
                "\n- Bar chart: after 2 country selected, the user can display a chart of the data from clicking on the checkbox"
                "\n - st.expender: display simple info about covid and the api by clicking on it"

                "\n\nCrypto page:"
                "\n- st.selectbox: choose the crypto from the select box"
                "\n- st.info: display data for the selected crypto"
                "\n- st.info / st.warning: display description if possible for the selected crypto."
                "\n- Table: table with the data of the selected crypto. Each time a crypto is selected add it to the table. Add color to each cells depanding of the crypto variation"
                "\n- Line chart: display the variation of the selected crypto"
                "\n- st.number_input: convert the amount choosen to the crypto price"
                "\n- st.radio: choose the currency to convert from")
        st.write("Romain Roux, Jorge Ortega, Zaid Gonzalez, Brain Pineda, Chris Puig, Steven Bencomo")
