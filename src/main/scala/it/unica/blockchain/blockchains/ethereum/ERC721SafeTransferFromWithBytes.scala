package it.unica.blockchain.blockchains.ethereum

import java.util.Date

import org.web3j.abi.TypeDecoder
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt

class ERC721SafeTransferFromWithBytes (
                                        hash: String,
                                        date: Date,

                                        nonce: BigInt,
                                        blockHash: String,
                                        blockHeight: BigInt,
                                        transactionIndex: BigInt,
                                        from: EthereumAddress,
                                        to: EthereumAddress,
                                        value: BigInt,
                                        gasPrice: BigInt,
                                        gas: BigInt,
                                        input: String,
                                        addressCreated: EthereumAddress,
                                        publicKey: String,
                                        raw: String,
                                        r: String,
                                        s: String,
                                        v: Int,

                                        contract: EthereumContract,
                                        requestOpt: Option[Request[_, EthGetTransactionReceipt]],

                                        val method : String,
                                        val tokenFrom :EthereumAddress,
                                        val tokenTo :EthereumAddress,
                                        val tokenId :Uint256,
                                        val tokenBytes :Object
                                      ) extends ERC721Transaction(hash, date, nonce, blockHash, blockHeight, transactionIndex, from, to, value, gasPrice, gas, input, addressCreated, publicKey, raw, r, s, v, contract, requestOpt) {



}

object ERC721SafeTransferFromWithBytes{

  def getInputData(inputData :String) :(String, EthereumAddress, EthereumAddress, Uint256, Object) ={
    val argDim = 64
    val firstArg = 10
    val secondArg = firstArg + argDim
    val thirdArg = secondArg + argDim
    val fourthArg = thirdArg + argDim

    val method :String = "safeTransferFrom(address _from, address _to, uint256 _tokenId, bytes data)"

    val from :String = inputData.substring(firstArg, secondArg)
    val to :String = inputData.substring(secondArg, thirdArg)
    val id :String = inputData.substring(thirdArg, fourthArg)
    val bytes :String = inputData.substring(fourthArg)

    val refMethod = classOf[TypeDecoder].getDeclaredMethod("decode", classOf[String], classOf[Class[_]])
    refMethod.setAccessible(true)

    val addressFrom = refMethod.invoke(null, from, classOf[Address]).asInstanceOf[Address]
    val ethAddressFrom = new EthereumAddress(addressFrom.toString)

    val addressTo = refMethod.invoke(null, to, classOf[Address]).asInstanceOf[Address]
    val ethAddressTo = new EthereumAddress(addressTo.toString)

    val tokenId = refMethod.invoke(null, id, classOf[Uint256]).asInstanceOf[Uint256]

    val tokenBytes = refMethod.invoke(null, bytes, classOf[Object])

    return (method, ethAddressFrom, ethAddressTo, tokenId, tokenBytes)
  }
}