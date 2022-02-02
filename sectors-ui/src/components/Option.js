import React from "react";

export default function Option({option, level}) {

    function addIndent(sectorName) {
        return '\u00A0\u00A0\u00A0'.repeat(level) + sectorName
    }

    const sector = option;

    return (
        <option label={addIndent(sector.sector)}
                value={sector.id}>{addIndent(sector.sector)}</option>
    )
}