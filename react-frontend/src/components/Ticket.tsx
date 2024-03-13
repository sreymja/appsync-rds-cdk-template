import {FormControl, TextField} from "@mui/material";
import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {generateClient} from "aws-amplify/api";
import {Ticket} from "../API";
import {getTicketById} from "../graphql/queries";
import Box from "@mui/material/Box";

export default function TicketComponent (){
    const params = useParams();
    const client = generateClient();
    const [ticket, setTicket] = useState<Ticket>()

    const getTicket = async (ticketId: number) => {
        const result = await client.graphql({
            query: getTicketById,
            variables: { id: ticketId }
        });
        // @ts-ignore
        setTicket(result.data.getTicketById[0])
    }

    useEffect(() => {
        if(params.id !== undefined){
            const id = +(params.id)
            getTicket(id);
        }
    }, [params])


    if(ticket == null) return <></>

    return (
        <Box
            component="form"
            sx={{
                '& .MuiTextField-root': {m: 1, width: '75ch'},
            }}
            noValidate
            autoComplete="off"
        >
            <FormControl>
                <TextField defaultValue={ticket?.subject} label={"Subject"} inputProps={{shrink: true}}></TextField>
                <TextField multiline={true} minRows={20} defaultValue={ticket?.content} label={"Content"} inputProps={{shrink: true}}></TextField>
            </FormControl>
        </Box>
    );
}