import { generateClient } from 'aws-amplify/api';
import * as queries from '../graphql/queries';
import * as React from 'react';
import {Link} from 'react-router-dom';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './Title';
import {useEffect, useState} from "react";
import {Ticket} from "../API";
import EditIcon from '@mui/icons-material/Edit';



export default function Tickets() {
    const [tickets, setTickets] = useState<Ticket[]>([]);
    const getTickets = async () => {
        const client = generateClient()
        const result = await client.graphql({
            query: queries.tickets,
        });
        // @ts-ignore
        setTickets(result.data.tickets)
    };
    useEffect(() => {
        getTickets()
    }, [])

    return (
        <React.Fragment>
            <Title>Tickets</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>Id</TableCell>
                        <TableCell>Subject</TableCell>
                        <TableCell>Created</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {tickets?.map((row) => (
                        <TableRow key={row?.id}>
                            <TableCell>{row?.id}</TableCell>
                            <TableCell>{row?.subject}</TableCell>
                            <TableCell>{row?.created_at}</TableCell>
                            <TableCell><Link to={`ticket/${row?.id}`}><EditIcon/></Link></TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </React.Fragment>
    );
}