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
import PriorityIcon from "./PriorityIcon";
import CategoryIcon from "./CategoryIcon";
import renderDate from "../utils/render-date";
import status from "../utils/status";




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
                        <TableCell></TableCell>
                        <TableCell></TableCell>
                        <TableCell>Status</TableCell>
                        <TableCell>Id</TableCell>
                        <TableCell>Subject</TableCell>
                        <TableCell>Created</TableCell>
                        <TableCell>Updated</TableCell>
                        <TableCell></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {tickets?.map((row) => (
                        <TableRow key={row?.id}>
                            <TableCell><PriorityIcon id={row?.priority_id}/></TableCell>
                            <TableCell><CategoryIcon id={row?.category_id}/></TableCell>
                            <TableCell>{status(row?.status_id || 0)}</TableCell>
                            <TableCell>{row?.id}</TableCell>
                            <TableCell>{row?.subject}</TableCell>
                            <TableCell>{renderDate(row?.created_at)}</TableCell>
                            <TableCell>{renderDate(row?.updated_at)}</TableCell>
                            <TableCell><Link to={`ticket/${row?.id}`}><EditIcon/></Link></TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </React.Fragment>
    );
}