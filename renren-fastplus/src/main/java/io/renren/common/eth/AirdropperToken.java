package io.renren.common.eth;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class AirdropperToken extends Contract {
    public static final  String tokenAddress="0x35f97dd6662a445bb69f49cf0142805d69437e42";
    private static final String BINARY = "606060405260008054600160a060020a033316600160a060020a03199091161790556102d1806100306000396000f3006060604052600436106100565763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416638da5cb5b811461005b578063ad8733ca1461008a578063f2fde38b14610139575b600080fd5b341561006657600080fd5b61006e61015a565b604051600160a060020a03909116815260200160405180910390f35b341561009557600080fd5b61012760048035600160a060020a03169060446024803590810190830135806020808202016040519081016040528093929190818152602001838360200280828437820191505050505050919080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284375094965061016995505050505050565b60405190815260200160405180910390f35b341561014457600080fd5b610158600160a060020a036004351661024f565b005b600054600160a060020a031681565b60008054819033600160a060020a0390811691161461018757600080fd5b5060005b83518110156102475784600160a060020a031663a9059cbb8583815181106101af57fe5b906020019060200201518584815181106101c557fe5b906020019060200201516040517c010000000000000000000000000000000000000000000000000000000063ffffffff8516028152600160a060020a0390921660048301526024820152604401600060405180830381600087803b151561022b57600080fd5b6102c65a03f1151561023c57600080fd5b50505060010161018b565b949350505050565b60005433600160a060020a0390811691161461026a57600080fd5b600160a060020a038116156102a2576000805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0383161790555b505600a165627a7a723058207b1cae783904f4099317a71b1f93ff26f8b88db81985989dc9b44e77cf87b9260029";

    public AirdropperToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AirdropperToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> multisend(String _tokenAddr, List<String> dests, List<BigInteger> values) {
        Function function = new Function(
                "multisend", 
                Arrays.<Type>asList(new Address(_tokenAddr),
                new org.web3j.abi.datatypes.DynamicArray<Address>(
                        org.web3j.abi.Utils.typeMap(dests, Address.class)),
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(values, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String newOwner) {
        Function function = new Function(
                "transferOwnership", 
                Arrays.<Type>asList(new Address(newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<AirdropperToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AirdropperToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<AirdropperToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AirdropperToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static AirdropperToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AirdropperToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static AirdropperToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AirdropperToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
