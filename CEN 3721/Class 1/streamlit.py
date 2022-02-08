import streamlit as st

st.title("Learning streamlit")
st.header("Human computer interaction")
st.subheader("Basic features")

first_name=st.text_input("first name")
last_name=st.text_input("last name")

st.write("hi", first_name, last_name,"welcome to hci")