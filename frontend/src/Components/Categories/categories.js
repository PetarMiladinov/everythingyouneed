import React from "react";

const categories = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped"}>
                        <thead>
                         <tr>
                             <th scope={"col"}> // shopraj scope
                                 Name
                             </th>
                         </tr>
                        </thead>
                        <tb>
                            {props.categories.map((term) =>{
                                return (
                                    <tr>
                                        <td>{term.name}</td>
                                    </tr>
                                )
                            })}
                        </tb>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default categories;