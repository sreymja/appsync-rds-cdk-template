import { generateClient } from 'aws-amplify/api';
import * as queries from '../graphql/queries';
import * as React from 'react';
import Link from '@mui/material/Link';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './Title';
const client = generateClient();

const result = await client.graphql({
    query: queries.tickets,
    // variables: { input: todoDetails }
    // variables: {
    //     // Fetch first 20 records
    //     limit: 20
    // }
});

const { tickets} = result.data;


export default function Tickets() {
    return (
        <React.Fragment>
            <Title>Tickets</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>Date</TableCell>
                        <TableCell>Name</TableCell>
                        <TableCell>Ship To</TableCell>
                        <TableCell>Payment Method</TableCell>
                        <TableCell align="right">Sale Amount</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {result.data.tickets?.map((row) => (
                        <TableRow key={row?.id}>
                            <TableCell>{row?.subject}</TableCell>
                            <TableCell>{row?.created_at}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </React.Fragment>
    );
}