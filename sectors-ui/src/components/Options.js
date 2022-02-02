import React, {useEffect, useState} from "react";
import Option from "./Option";

export default function Options({sectorsArray, setSelects}) {

    const [sectors, setSectors] = useState()
    const [selectedOptions, setSelectedOptions] = useState([]);

    useEffect(() => {
        setSectors(sectorsArray)
    })

    function renderOption(sectors, hierarchyLevel = 0) {
        const level = hierarchyLevel;
        if (sectors && sectors.length > 0) {

            return sectors.map((option) => {
                return (
                    <>
                        <Option option={option} level={level}/>
                        {
                            renderOption(option.sectors, level + 1)
                        }
                    </>
                )
            })
        }
    }

    function setParentIdWrapper(e) {

        let index = selectedOptions.indexOf(e.target.value);
        if (index !== -1) {
            selectedOptions.splice(index, 1);
        } else {
            selectedOptions.push(e.target.value)
        }
        setSelects(selectedOptions)
    }

    return (
        <select value={selectedOptions} className="select" multiple="30"
                onChange={(event => setParentIdWrapper(event))}>
            {renderOption(sectors)}
        </select>
    )
}