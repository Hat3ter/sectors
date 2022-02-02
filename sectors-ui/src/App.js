import './App.css';
import React, {useEffect, useState} from "react";
import Options from "./components/Options";
import axios from "axios";

const sectorsUrl = `${process.env.REACT_APP_URL_SECTOR_API}`;

function App() {

    const [userId, setUserId] = useState()
    const [name, setName] = useState('')
    const [sectorIds, setSectorIds] = useState([])
    const [agreeTerms, setAgreeTerms] = useState(false)
    const [status, setStatus] = useState('')
    const [sectorsArray, setSectors] = useState([])


    useEffect(() => {
        if (!userId) {
            getSectors()
        }
    }, [])


    const getSectors = () => {

        axios.get(`${sectorsUrl}/sectors`)
            .then((response) => {
                setSectors(response.data.data)
            })
            .catch(error => {
                console.error(error)
                setStatus("can't fetch data from server")
            })
    }

    const getSectorsByUserId = (id) => {

        axios.get(`${sectorsUrl}/sectors/${id}`)
            .then((response) => {
                setSectors(response.data.data.sectorDto)
            })
            .catch(error => {
                console.error(error)
                setStatus("can't fetch data from server")
            })
    }


    function saveSector(e) {

        e.preventDefault()
        axios.post(`${sectorsUrl}/users`, {
                id: userId,
                name: name,
                sectorIds: sectorIds,
                agreeWithTerms: agreeTerms
            }
        )
            .then((response) => {
                setUserId(response.data.data)
                getSectorsByUserId(response.data.data)
            })
            .catch(error => {
                console.log(error)
                setStatus(`save failed: ${error.response.data.message}`)
            })
    }

    return (
        <div className="App">
            <p className="info">{status}</p>
            <div>
                <p> Please enter your name and pick the Sectors you are currently involved in.</p>
                <form>
                    <div className="container">
                        <label>Name: </label>
                        <input type="text" value={name} onChange={(event => setName(event.target.value))}/>
                    </div>
                    <div className="container">
                        <label>Sectors: </label>
                        <Options sectorsArray={sectorsArray} setSelects={setSectorIds}/>
                    </div>
                    <div className="container">
                        <input type="checkbox" value={agreeTerms} onChange={() => setAgreeTerms(!agreeTerms)}/>
                        Agree to terms
                    </div>
                    <button className="button" disabled={!agreeTerms || name.length === 0} onClick={saveSector}>
                        Save
                    </button>

                </form>
            </div>
        </div>
    );
}

export default App;
