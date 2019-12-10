package com.example.demo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint8;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple13;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class Money extends Contract {
    public static String BINARY = "608060405234801561001057600080fd5b5033600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600080819055506000600181905550612475806100706000396000f3006080604052600436106100e6576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806309b89664146100eb5780630f7ee1ec1461013057806312bc20b8146103315780631cc8743c146103765780631d143848146103cc5780632a45eee6146104235780632bfe774c1461046857806346b50a64146105555780634fd0ede51461059a578063572e491c146105df5780637868c4881461060a57806378f507d81461064f5780639377d7111461067a578063982b09ee146106c2578063a271de2514610707578063f0cc67111461074c575b600080fd5b3480156100f757600080fd5b5061011660048036038101908080359060200190929190505050610791565b604051808215151515815260200191505060405180910390f35b34801561013c57600080fd5b5061015b60048036038101908080359060200190929190505050610877565b604051808e73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018d73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018c81526020018b1515151581526020018a60ff1660ff1681526020018973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200188151515158152602001871515151581526020018615151515815260200185151515158152602001841515151581526020018060200183810383528e818151815260200191508051906020019080838360005b83811015610283578082015181840152602081019050610268565b50505050905090810190601f1680156102b05780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156102e95780820151818401526020810190506102ce565b50505050905090810190601f1680156103165780820380516001836020036101000a031916815260200191505b509f5050505050505050505050505050505060405180910390f35b34801561033d57600080fd5b5061035c60048036038101908080359060200190929190505050610ad7565b604051808215151515815260200191505060405180910390f35b34801561038257600080fd5b506103ab6004803603810190808035906020019092919080359060200190929190505050610b87565b60405180831515151581526020018281526020019250505060405180910390f35b3480156103d857600080fd5b506103e1611447565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561042f57600080fd5b5061044e6004803603810190808035906020019092919050505061146d565b604051808215151515815260200191505060405180910390f35b34801561047457600080fd5b5061053f600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506116da565b6040518082815260200191505060405180910390f35b34801561056157600080fd5b50610580600480360381019080803590602001909291905050506119e7565b604051808215151515815260200191505060405180910390f35b3480156105a657600080fd5b506105c560048036038101908080359060200190929190505050611acd565b604051808215151515815260200191505060405180910390f35b3480156105eb57600080fd5b506105f4611bb4565b6040518082815260200191505060405180910390f35b34801561061657600080fd5b5061063560048036038101908080359060200190929190505050611bba565b604051808215151515815260200191505060405180910390f35b34801561065b57600080fd5b50610664611d9e565b6040518082815260200191505060405180910390f35b34801561068657600080fd5b506106a560048036038101908080359060200190929190505050611da4565b604051808381526020018281526020019250505060405180910390f35b3480156106ce57600080fd5b506106ed60048036038101908080359060200190929190505050611dd7565b604051808215151515815260200191505060405180910390f35b34801561071357600080fd5b506107326004803603810190808035906020019092919050505061200f565b604051808215151515815260200191505060405180910390f35b34801561075857600080fd5b50610777600480360381019080803590602001909291905050506121e9565b604051808215151515815260200191505060405180910390f35b60008054821080156107f057503373ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b8015610826575060038281548110151561080657fe5b906000526020600020906006020160040160199054906101000a900460ff165b1561086d57600160038381548110151561083c57fe5b9060005260206000209060060201600401601a6101000a81548160ff02191690831515021790555060019050610872565b600090505b919050565b60038181548110151561088657fe5b90600052602060002090600602016000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806002018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561097e5780601f106109535761010080835404028352916020019161097e565b820191906000526020600020905b81548152906001019060200180831161096157829003601f168201915b5050505050908060030154908060040160009054906101000a900460ff16908060040160019054906101000a900460ff16908060040160029054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060040160169054906101000a900460ff16908060040160179054906101000a900460ff16908060040160189054906101000a900460ff16908060040160199054906101000a900460ff169080600401601a9054906101000a900460ff1690806005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610acd5780601f10610aa257610100808354040283529160200191610acd565b820191906000526020600020905b815481529060010190602001808311610ab057829003601f168201915b505050505090508d565b6000805482108015610b3657503373ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b15610b7d576001600383815481101515610b4c57fe5b906000526020600020906006020160040160006101000a81548160ff02191690831515021790555060019050610b82565b600090505b919050565b6000806000806000806000610b9a6122d0565b6000548a101580610bad57506000548910155b15610bbb576000975061143a565b600389815481101515610bca57fe5b906000526020600020906006020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1660038b815481101515610c2157fe5b906000526020600020906006020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614955060038a815481101515610c7b57fe5b906000526020600020906006020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1660038a815481101515610cd257fe5b906000526020600020906006020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16149450600389815481101515610d2c57fe5b90600052602060002090600602016003015460038b815481101515610d4d57fe5b90600052602060002090600602016003015414935060038a815481101515610d7157fe5b906000526020600020906006020160040160009054906101000a900460ff168015610dc65750600389815481101515610da657fe5b906000526020600020906006020160040160009054906101000a900460ff165b8015610e025750600060038b815481101515610dde57fe5b906000526020600020906006020160040160019054906101000a900460ff1660ff16145b8015610e3e5750600060038a815481101515610e1a57fe5b906000526020600020906006020160040160019054906101000a900460ff1660ff16145b8015610e75575060038a815481101515610e5457fe5b906000526020600020906006020160040160179054906101000a900460ff16155b8015610eac5750600389815481101515610e8b57fe5b906000526020600020906006020160040160179054906101000a900460ff16155b8015610ee3575060038a815481101515610ec257fe5b906000526020600020906006020160040160199054906101000a900460ff16155b8015610f1a5750600389815481101515610ef957fe5b906000526020600020906006020160040160199054906101000a900460ff16155b92508515610f94573373ffffffffffffffffffffffffffffffffffffffff1660038b815481101515610f4857fe5b906000526020600020906006020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161491505b841561100c573373ffffffffffffffffffffffffffffffffffffffff1660038a815481101515610fc057fe5b906000526020600020906006020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161491505b85806110155750845b801561102857508580156110265750845b155b80156110315750835b801561103a5750825b80156110435750815b1561143457851561120557600160038b81548110151561105f57fe5b906000526020600020906006020160040160016101000a81548160ff021916908360ff160217905550600260038a81548110151561109957fe5b906000526020600020906006020160040160016101000a81548160ff021916908360ff1602179055506003898154811015156110d157fe5b906000526020600020906006020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660038b81548110151561111257fe5b906000526020600020906006020160040160026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060038a81548110151561117057fe5b906000526020600020906006020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660038a8154811015156111b157fe5b906000526020600020906006020160040160026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506113b8565b600260038b81548110151561121657fe5b906000526020600020906006020160040160016101000a81548160ff021916908360ff160217905550600160038a81548110151561125057fe5b906000526020600020906006020160040160016101000a81548160ff021916908360ff16021790555060038981548110151561128857fe5b906000526020600020906006020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660038b8154811015156112c957fe5b906000526020600020906006020160040160026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060038a81548110151561132757fe5b906000526020600020906006020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660038a81548110151561136857fe5b906000526020600020906006020160040160026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b60408051908101604052808b81526020018a8152509050600481908060018154018082558091505090600182039060005260206000209060020201600090919290919091506000820151816000015560208201518160010155505050600154965060016000815480929190600101919050555060019750611439565b600097505b5b5050505050509250929050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060008054841080156114ed57503373ffffffffffffffffffffffffffffffffffffffff166003858154811015156114a357fe5b906000526020600020906006020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b801561152a5750600260038581548110151561150557fe5b906000526020600020906006020160040160019054906101000a900460ff1660ff1614155b915060005484108015611567575060038481548110151561154757fe5b906000526020600020906006020160040160009054906101000a900460ff165b80156115d85750600060038581548110151561157f57fe5b906000526020600020906006020160040160019054906101000a900460ff1660ff1614806115d757506003848154811015156115b757fe5b906000526020600020906006020160040160169054906101000a900460ff165b5b801561164457506003848154811015156115ee57fe5b906000526020600020906006020160040160179054906101000a900460ff161580611643575060038481548110151561162357fe5b906000526020600020906006020160040160189054906101000a900460ff165b5b801561167b575060038481548110151561165a57fe5b906000526020600020906006020160040160199054906101000a900460ff16155b90508180156116875750805b156116ce57600160038581548110151561169d57fe5b906000526020600020906006020160040160196101000a81548160ff021916908315150217905550600192506116d3565b600092505b5050919050565b60006116e46122ea565b6101a0604051908101604052803373ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff168152602001878152602001858152602001600015158152602001600060ff168152602001600073ffffffffffffffffffffffffffffffffffffffff1681526020016000151581526020016000151581526020016000151581526020016000151581526020016000151581526020018481525090506003819080600181540180825580915050906001820390600052602060002090600602016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020190805190602001906118709291906123a4565b506060820151816003015560808201518160040160006101000a81548160ff02191690831515021790555060a08201518160040160016101000a81548160ff021916908360ff16021790555060c08201518160040160026101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060e08201518160040160166101000a81548160ff0219169083151502179055506101008201518160040160176101000a81548160ff0219169083151502179055506101208201518160040160186101000a81548160ff0219169083151502179055506101408201518160040160196101000a81548160ff02191690831515021790555061016082015181600401601a6101000a81548160ff0219169083151502179055506101808201518160050190805190602001906119c49291906123a4565b505050506000549150600080815480929190600101919050555050949350505050565b6000805482108015611a4657503373ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b8015611a7c5750600382815481101515611a5c57fe5b906000526020600020906006020160040160179054906101000a900460ff165b15611ac3576001600383815481101515611a9257fe5b906000526020600020906006020160040160186101000a81548160ff02191690831515021790555060019050611ac8565b600090505b919050565b6000805482108015611b2c57503373ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b8015611b635750600382815481101515611b4257fe5b9060005260206000209060060201600401601a9054906101000a900460ff16155b15611baa576000600383815481101515611b7957fe5b906000526020600020906006020160040160196101000a81548160ff02191690831515021790555060019050611baf565b600090505b919050565b60005481565b600060015482108015611c1a57503373ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b8015611c77575060006003600484815481101515611c3457fe5b906000526020600020906002020160000154815481101515611c5257fe5b906000526020600020906006020160040160019054906101000a900460ff1660ff1614155b8015611cd4575060006003600484815481101515611c9157fe5b906000526020600020906002020160010154815481101515611caf57fe5b906000526020600020906006020160040160019054906101000a900460ff1660ff1614155b15611d945760016003600484815481101515611cec57fe5b906000526020600020906002020160000154815481101515611d0a57fe5b906000526020600020906006020160040160166101000a81548160ff02191690831515021790555060016003600484815481101515611d4557fe5b906000526020600020906002020160010154815481101515611d6357fe5b906000526020600020906006020160040160166101000a81548160ff02191690831515021790555060019050611d99565b600090505b919050565b60015481565b600481815481101515611db357fe5b90600052602060002090600202016000915090508060000154908060010154905082565b6000806000805484108015611e5757503373ffffffffffffffffffffffffffffffffffffffff16600385815481101515611e0d57fe5b906000526020600020906006020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b8015611e9457506002600385815481101515611e6f57fe5b906000526020600020906006020160040160019054906101000a900460ff1660ff1614155b915060005484108015611ed15750600384815481101515611eb157fe5b906000526020600020906006020160040160009054906101000a900460ff165b8015611f4257506000600385815481101515611ee957fe5b906000526020600020906006020160040160019054906101000a900460ff1660ff161480611f415750600384815481101515611f2157fe5b906000526020600020906006020160040160169054906101000a900460ff165b5b8015611f795750600384815481101515611f5857fe5b906000526020600020906006020160040160179054906101000a900460ff16155b8015611fb05750600384815481101515611f8f57fe5b906000526020600020906006020160040160199054906101000a900460ff16155b9050818015611fbc5750805b15612003576001600385815481101515611fd257fe5b906000526020600020906006020160040160176101000a81548160ff02191690831515021790555060019250612008565b600092505b5050919050565b60006001548210801561206f57503373ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b80156120c65750600360048381548110151561208757fe5b9060005260206000209060020201600001548154811015156120a557fe5b906000526020600020906006020160040160169054906101000a900460ff16155b801561211d575060036004838154811015156120de57fe5b9060005260206000209060020201600101548154811015156120fc57fe5b906000526020600020906006020160040160169054906101000a900460ff16155b156121df576000600360048481548110151561213557fe5b90600052602060002090600202016000015481548110151561215357fe5b906000526020600020906006020160040160016101000a81548160ff021916908360ff1602179055506000600360048481548110151561218f57fe5b9060005260206000209060020201600101548154811015156121ad57fe5b906000526020600020906006020160040160016101000a81548160ff021916908360ff160217905550600190506121e4565b600090505b919050565b600080548210801561224857503373ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b801561227f575060038281548110151561225e57fe5b906000526020600020906006020160040160189054906101000a900460ff16155b156122c657600060038381548110151561229557fe5b906000526020600020906006020160040160176101000a81548160ff021916908315150217905550600190506122cb565b600090505b919050565b604080519081016040528060008152602001600081525090565b6101a060405190810160405280600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff1681526020016060815260200160008152602001600015158152602001600060ff168152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600015158152602001600015158152602001600015158152602001600015158152602001600015158152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106123e557805160ff1916838001178555612413565b82800160010185558215612413579182015b828111156124125782518255916020019190600101906123f7565b5b5090506124209190612424565b5090565b61244691905b8082111561244257600081600090555060010161242a565b5090565b905600a165627a7a7230582011036dd1dc16636bb070c63025779be751475180c2fe8d652b69e28eb7659ec80029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"VerifySettle\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"receipts\",\"outputs\":[{\"name\":\"obligor\",\"type\":\"address\"},{\"name\":\"obligee\",\"type\":\"address\"},{\"name\":\"datetime\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"uint256\"},{\"name\":\"verified\",\"type\":\"bool\"},{\"name\":\"transfer\",\"type\":\"uint8\"},{\"name\":\"transferTo\",\"type\":\"address\"},{\"name\":\"transferVerified\",\"type\":\"bool\"},{\"name\":\"discount\",\"type\":\"bool\"},{\"name\":\"discountVerified\",\"type\":\"bool\"},{\"name\":\"settle\",\"type\":\"bool\"},{\"name\":\"settleVerified\",\"type\":\"bool\"},{\"name\":\"note\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"VerifyPurchase\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"uint256\"},{\"name\":\"b\",\"type\":\"uint256\"}],\"name\":\"TransferTwoReceipts\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"},{\"name\":\"transfer_index\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"issuer\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"Settle\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"datetime\",\"type\":\"string\"},{\"name\":\"obligee\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"},{\"name\":\"reason\",\"type\":\"string\"}],\"name\":\"Purchase\",\"outputs\":[{\"name\":\"receipt_index\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"VerifyDiscount\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"CancelSettle\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"receipt_counter\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"VerifyTransfer\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"transfer_counter\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"transfers\",\"outputs\":[{\"name\":\"a\",\"type\":\"uint256\"},{\"name\":\"b\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"Discount\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"CancelTransfer\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"CancelDiscount\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_VERIFYSETTLE = "VerifySettle";

    public static final String FUNC_RECEIPTS = "receipts";

    public static final String FUNC_VERIFYPURCHASE = "VerifyPurchase";

    public static final String FUNC_TRANSFERTWORECEIPTS = "TransferTwoReceipts";

    public static final String FUNC_ISSUER = "issuer";

    public static final String FUNC_SETTLE = "Settle";

    public static final String FUNC_PURCHASE = "Purchase";

    public static final String FUNC_VERIFYDISCOUNT = "VerifyDiscount";

    public static final String FUNC_CANCELSETTLE = "CancelSettle";

    public static final String FUNC_RECEIPT_COUNTER = "receipt_counter";

    public static final String FUNC_VERIFYTRANSFER = "VerifyTransfer";

    public static final String FUNC_TRANSFER_COUNTER = "transfer_counter";

    public static final String FUNC_TRANSFERS = "transfers";

    public static final String FUNC_DISCOUNT = "Discount";

    public static final String FUNC_CANCELTRANSFER = "CancelTransfer";

    public static final String FUNC_CANCELDISCOUNT = "CancelDiscount";

    @Deprecated
    protected Money(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Money(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Money(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Money(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> VerifySettle(BigInteger index) {
        final Function function = new Function(
                FUNC_VERIFYSETTLE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void VerifySettle(BigInteger index, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_VERIFYSETTLE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String VerifySettleSeq(BigInteger index) {
        final Function function = new Function(
                FUNC_VERIFYSETTLE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getVerifySettleInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_VERIFYSETTLE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public Tuple1<Boolean> getVerifySettleOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_VERIFYSETTLE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public RemoteCall<Tuple13<String, String, String, BigInteger, Boolean, BigInteger, String, Boolean, Boolean, Boolean, Boolean, Boolean, String>> receipts(BigInteger param0) {
        final Function function = new Function(FUNC_RECEIPTS,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple13<String, String, String, BigInteger, Boolean, BigInteger, String, Boolean, Boolean, Boolean, Boolean, Boolean, String>>(
                new Callable<Tuple13<String, String, String, BigInteger, Boolean, BigInteger, String, Boolean, Boolean, Boolean, Boolean, Boolean, String>>() {
                    @Override
                    public Tuple13<String, String, String, BigInteger, Boolean, BigInteger, String, Boolean, Boolean, Boolean, Boolean, Boolean, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple13<String, String, String, BigInteger, Boolean, BigInteger, String, Boolean, Boolean, Boolean, Boolean, Boolean, String>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (Boolean) results.get(4).getValue(),
                                (BigInteger) results.get(5).getValue(),
                                (String) results.get(6).getValue(),
                                (Boolean) results.get(7).getValue(),
                                (Boolean) results.get(8).getValue(),
                                (Boolean) results.get(9).getValue(),
                                (Boolean) results.get(10).getValue(),
                                (Boolean) results.get(11).getValue(),
                                (String) results.get(12).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> VerifyPurchase(BigInteger index) {
        final Function function = new Function(
                FUNC_VERIFYPURCHASE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void VerifyPurchase(BigInteger index, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_VERIFYPURCHASE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String VerifyPurchaseSeq(BigInteger index) {
        final Function function = new Function(
                FUNC_VERIFYPURCHASE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getVerifyPurchaseInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_VERIFYPURCHASE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public Tuple1<Boolean> getVerifyPurchaseOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_VERIFYPURCHASE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public RemoteCall<TransactionReceipt> TransferTwoReceipts(BigInteger a, BigInteger b) {
        final Function function = new Function(
                FUNC_TRANSFERTWORECEIPTS,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(a),
                        new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void TransferTwoReceipts(BigInteger a, BigInteger b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERTWORECEIPTS,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(a),
                        new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String TransferTwoReceiptsSeq(BigInteger a, BigInteger b) {
        final Function function = new Function(
                FUNC_TRANSFERTWORECEIPTS,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(a),
                        new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<BigInteger, BigInteger> getTransferTwoReceiptsInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFERTWORECEIPTS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue()
        );
    }

    public Tuple2<Boolean, BigInteger> getTransferTwoReceiptsOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFERTWORECEIPTS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<Boolean, BigInteger>(

                (Boolean) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue()
        );
    }

    public RemoteCall<String> issuer() {
        final Function function = new Function(FUNC_ISSUER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> Settle(BigInteger index) {
        final Function function = new Function(
                FUNC_SETTLE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void Settle(BigInteger index, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETTLE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String SettleSeq(BigInteger index) {
        final Function function = new Function(
                FUNC_SETTLE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getSettleInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETTLE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public Tuple1<Boolean> getSettleOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SETTLE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public RemoteCall<TransactionReceipt> Purchase(String datetime, String obligee, BigInteger amount, String reason) {
        final Function function = new Function(
                FUNC_PURCHASE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(datetime),
                        new org.fisco.bcos.web3j.abi.datatypes.Address(obligee),
                        new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(reason)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void Purchase(String datetime, String obligee, BigInteger amount, String reason, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PURCHASE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(datetime),
                        new org.fisco.bcos.web3j.abi.datatypes.Address(obligee),
                        new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(reason)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String PurchaseSeq(String datetime, String obligee, BigInteger amount, String reason) {
        final Function function = new Function(
                FUNC_PURCHASE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(datetime),
                        new org.fisco.bcos.web3j.abi.datatypes.Address(obligee),
                        new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount),
                        new org.fisco.bcos.web3j.abi.datatypes.Utf8String(reason)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, BigInteger, String> getPurchaseInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_PURCHASE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, BigInteger, String>(

                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue(),
                (String) results.get(3).getValue()
        );
    }

    public Tuple1<BigInteger> getPurchaseOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_PURCHASE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public RemoteCall<TransactionReceipt> VerifyDiscount(BigInteger index) {
        final Function function = new Function(
                FUNC_VERIFYDISCOUNT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void VerifyDiscount(BigInteger index, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_VERIFYDISCOUNT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String VerifyDiscountSeq(BigInteger index) {
        final Function function = new Function(
                FUNC_VERIFYDISCOUNT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getVerifyDiscountInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_VERIFYDISCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public Tuple1<Boolean> getVerifyDiscountOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_VERIFYDISCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public RemoteCall<TransactionReceipt> CancelSettle(BigInteger index) {
        final Function function = new Function(
                FUNC_CANCELSETTLE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void CancelSettle(BigInteger index, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CANCELSETTLE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String CancelSettleSeq(BigInteger index) {
        final Function function = new Function(
                FUNC_CANCELSETTLE,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getCancelSettleInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CANCELSETTLE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public Tuple1<Boolean> getCancelSettleOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CANCELSETTLE,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public RemoteCall<BigInteger> receipt_counter() {
        final Function function = new Function(FUNC_RECEIPT_COUNTER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> VerifyTransfer(BigInteger index) {
        final Function function = new Function(
                FUNC_VERIFYTRANSFER,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void VerifyTransfer(BigInteger index, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_VERIFYTRANSFER,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String VerifyTransferSeq(BigInteger index) {
        final Function function = new Function(
                FUNC_VERIFYTRANSFER,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getVerifyTransferInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_VERIFYTRANSFER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public Tuple1<Boolean> getVerifyTransferOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_VERIFYTRANSFER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public RemoteCall<BigInteger> transfer_counter() {
        final Function function = new Function(FUNC_TRANSFER_COUNTER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> transfers(BigInteger param0) {
        final Function function = new Function(FUNC_TRANSFERS,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> Discount(BigInteger index) {
        final Function function = new Function(
                FUNC_DISCOUNT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void Discount(BigInteger index, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_DISCOUNT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String DiscountSeq(BigInteger index) {
        final Function function = new Function(
                FUNC_DISCOUNT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getDiscountInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DISCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public Tuple1<Boolean> getDiscountOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_DISCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public RemoteCall<TransactionReceipt> CancelTransfer(BigInteger index) {
        final Function function = new Function(
                FUNC_CANCELTRANSFER,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void CancelTransfer(BigInteger index, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CANCELTRANSFER,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String CancelTransferSeq(BigInteger index) {
        final Function function = new Function(
                FUNC_CANCELTRANSFER,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getCancelTransferInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CANCELTRANSFER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public Tuple1<Boolean> getCancelTransferOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CANCELTRANSFER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    public RemoteCall<TransactionReceipt> CancelDiscount(BigInteger index) {
        final Function function = new Function(
                FUNC_CANCELDISCOUNT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void CancelDiscount(BigInteger index, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CANCELDISCOUNT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String CancelDiscountSeq(BigInteger index) {
        final Function function = new Function(
                FUNC_CANCELDISCOUNT,
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getCancelDiscountInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CANCELDISCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
        );
    }

    public Tuple1<Boolean> getCancelDiscountOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CANCELDISCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
        );
    }

    @Deprecated
    public static Money load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Money(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Money load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Money(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Money load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Money(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Money load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Money(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Money> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Money.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Money> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Money.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Money> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Money.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Money> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Money.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
