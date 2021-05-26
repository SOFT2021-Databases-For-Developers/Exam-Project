import React from "react";

function CharacterDropDown() {
    const [loading, setLoading] = React.useState(true);
    const [items, setItems] = React.useState([]);
    const [value, setValue] = React.useState("R2-D2");
    React.useEffect(() => {
      let unmounted = false;
      async function getCharacters() {
        const response = await fetch(
          "http://localhost:25001/makes"
        );
        const body = await response.json();
        if (!unmounted) {
          setItems(
            body.map(({ item }) => (console.log(item)))
          );
          setLoading(false);
        }
      }
      getCharacters();
      return () => {
        unmounted = true;
      };
    }, []);
  
    return (
      <select
        disabled={loading}
        value={value}
        onChange={(e) => setValue(e.currentTarget.value)}
      >
        {items.map(({ label, value }) => (
          <option key={value} value={value}>
            {label}
          </option>
        ))}
      </select>
    );
  }
  
  export default function Test() {
    return (
      <div className="App">
        <CharacterDropDown />
      </div>
    );
  }