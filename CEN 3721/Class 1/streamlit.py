import streamlit as st
import requests

st.title("Air quality index")

url = "https://api.airvisual.com/v2/near"
url_cities = "https://api.airvisual.com"

response=requests.get(url_cities).json()

st.write(response)